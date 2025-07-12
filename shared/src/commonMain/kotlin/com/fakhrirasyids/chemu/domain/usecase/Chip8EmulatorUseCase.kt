package com.fakhrirasyids.chemu.domain.usecase

import com.fakhrirasyids.chemu.domain.repo.Chip8Repository
import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.services.display.Display
import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard

/**
 * Author: @fakhrirasyids
 *
 * Use case class that exposes high-level CHIP-8 emulator operations to the presentation layer.
 *
 * Acts as an interface between the UI/ViewModel layer and the internal CHIP-8 emulator repository.
 * Encapsulates CPU execution, memory loading, and component access such as display and input.
 *
 * @property repo The CHIP-8 system repository providing CPU, memory, timers, display, and keyboard implementations.
 */
class Chip8EmulatorUseCase(
    private val repo: Chip8Repository
) {

    /**
     * Executes one CPU instruction cycle:
     * - Fetches the current opcode
     * - Decodes it
     * - Executes the corresponding instruction
     */
    fun cycle() = repo.cpu.cycle()

    /**
     * Fully resets the CHIP-8 system to its initial state:
     * - Clears CPU registers, display, memory, timers, and keyboard
     * - Reloads the built-in fontset
     */
    fun reset() = repo.resetSystem()

    /**
     * Loads a CHIP-8 ROM/program into memory.
     *
     * @param program The program's binary content.
     */
    fun loadProgram(program: ByteArray) = repo.loadProgram(program)

    /**
     * Decrements the delay and sound timers (typically at 60Hz).
     * Should be called independently from CPU execution cycles.
     */
    fun tickTimers() = repo.tickTimers()

    /**
     * Returns the CHIP-8 display service for rendering graphics.
     */
    fun getDisplay(): Display = repo.display

    /**
     * Returns the CHIP-8 keyboard service for handling input.
     */
    fun getKeyboard(): Keyboard = repo.keyboard

    /**
     * Returns the CPU service to access state and registers.
     */
    fun getCpu(): CPU = repo.cpu
}
