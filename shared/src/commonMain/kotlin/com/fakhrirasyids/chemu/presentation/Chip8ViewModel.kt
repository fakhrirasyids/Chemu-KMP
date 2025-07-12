package com.fakhrirasyids.chemu.presentation

import com.fakhrirasyids.chemu.domain.usecase.Chip8EmulatorUseCase
import com.fakhrirasyids.chemu.domain.usecase.RomUseCase
import com.fakhrirasyids.chemu.platform.PlatformDispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/**
 * Author: @fakhrirasyids
 *
 * ViewModel responsible for managing the UI logic and state of the CHIP-8 emulator.
 *
 * Handles ROM loading, tick speed configuration, debug mode, lifecycle-based
 * pause/resume behavior, and drives both CPU execution and display rendering loops.
 *
 * @param emulator Use case for core CHIP-8 execution logic and hardware access.
 * @param romUseCase Use case for fetching and loading ROM files.
 * @param platform Platform-specific coroutine dispatcher for executing work in appropriate threads.
 */
@OptIn(ExperimentalTime::class, ExperimentalUnsignedTypes::class)
class Chip8ViewModel(
    private val emulator: Chip8EmulatorUseCase,
    private val romUseCase: RomUseCase,
    private val platform: PlatformDispatcher
) {
    private var isRunning = false
    private var isPaused = false
    private var frameId = 0

    private var tickSpeedHz = 60
    private var debugMode = false

    private val _uiState = MutableStateFlow(Chip8UiState())
    /** Exposes current CHIP-8 UI state to the composable layer. */
    val uiState: StateFlow<Chip8UiState> = _uiState.asStateFlow()

    private val _romList = MutableStateFlow<List<String>>(emptyList())
    /** List of all available ROM file names. */
    val romList: StateFlow<List<String>> = _romList.asStateFlow()

    init {
        loadRomList()
    }

    /**
     * Updates the tick speed of the CHIP-8 CPU in Hz.
     *
     * @param hz The new tick speed in Hertz (e.g., 500).
     */
    fun setTickSpeed(hz: Int) {
        tickSpeedHz = hz
    }

    /**
     * Enables or disables debug mode. When enabled, more detailed CPU state
     * will be exposed in [Chip8UiState].
     *
     * @param enabled `true` to enable debug mode, `false` to disable.
     */
    fun setDebugMode(enabled: Boolean) {
        debugMode = enabled
    }

    /**
     * Loads the list of ROM names bundled in the application.
     * Invoked automatically at ViewModel init.
     */
    fun loadRomList() {
        platform.scope.launch {
            _romList.value = romUseCase.getRomList()
        }
    }

    /**
     * Loads and starts a ROM by its name (e.g., `"pong.ch8"`).
     *
     * @param name Name of the ROM to load.
     */
    fun loadAndStartRom(name: String) {
        platform.scope.launch {
            val rom = romUseCase.loadRom(name)
            start(rom)
        }
    }

    /**
     * Starts the CHIP-8 emulator with the given [rom] program.
     *
     * Initializes the system, starts the CPU cycle loop and UI frame update loop.
     * Subsequent invocations are ignored if already running.
     *
     * @param rom Byte array representing the loaded CHIP-8 ROM.
     */
    fun start(rom: ByteArray) {
        if (isRunning) return
        isRunning = true
        isPaused = false

        emulator.reset()
        emulator.loadProgram(rom)

        // CHIP-8 CPU + Timers execution loop
        platform.scope.launch {
            while (isRunning) {
                if (!isPaused) {
                    val elapsed = measureTime {
                        emulator.cycle()
                        emulator.tickTimers()
                    }
                    val delayMs = (1000 / tickSpeedHz) - elapsed.inWholeMilliseconds
                    if (delayMs > 0) delay(delayMs)
                } else {
                    delay(100)
                }
            }
        }

        // UI Display refresh loop
        platform.scope.launch {
            var lastFrame: Array<BooleanArray>? = null
            while (isRunning) {
                if (!isPaused && emulator.getDisplay().shouldDraw()) {
                    val frame = emulator.getDisplay().getPixels()
                    val copy = Array(64) { x -> BooleanArray(32) { y -> frame[x][y] } }
                    emulator.getDisplay().clearDrawFlag()

                    if (lastFrame == null || !copy.contentDeepEquals(lastFrame)) {
                        _uiState.emit(
                            Chip8UiState(
                                pixels = copy,
                                frameId = frameId++,
                                pc = emulator.getCpu().pc.toInt(),
                                i = emulator.getCpu().index.toInt(),
                                opcode = emulator.getCpu().lastOpcode.toInt(),
                                registers = emulator.getCpu().v.map { it.toInt() },
                                dt = emulator.getCpu().delayTimer.toInt(),
                                st = emulator.getCpu().soundTimer.toInt(),
                                stack = emulator.getCpu().stack.map { it.toInt() },
                                debug = debugMode
                            )
                        )
                        lastFrame = copy
                    }
                }
                delay(16)
            }
        }
    }

    /**
     * Stops the emulator and terminates running loops.
     */
    fun stop() {
        isRunning = false
    }

    /**
     * Temporarily pauses CPU execution (e.g., when lifecycle goes to background).
     */
    fun pause() {
        isPaused = true
    }

    /**
     * Resumes CPU execution after a pause.
     */
    fun resume() {
        isPaused = false
    }

    /**
     * Called when user interacts with the virtual keypad.
     *
     * @param key The CHIP-8 key index (0x0 - 0xF).
     * @param pressed `true` if pressed, `false` if released.
     */
    fun onKeyPress(key: Int, pressed: Boolean) {
        emulator.getKeyboard().setKeyPressed(key, pressed)
    }
}
