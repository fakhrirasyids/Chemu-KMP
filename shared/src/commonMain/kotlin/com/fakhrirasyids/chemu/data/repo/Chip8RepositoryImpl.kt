package com.fakhrirasyids.chemu.data.repo

import com.fakhrirasyids.chemu.data.cpu.CPUImpl
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.services.display.Display
import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard
import com.fakhrirasyids.chemu.domain.services.memory.Memory
import com.fakhrirasyids.chemu.domain.services.timers.Timers
import com.fakhrirasyids.chemu.domain.repo.Chip8Repository

/**
 * Author: @fakhrirasyids
 *
 * Implementation of [Chip8Repository] that manages the full CHIP-8 virtual machine.
 * This includes memory, display, keyboard, timers, and CPU core.
 *
 * Acts as a facade for initializing and running the emulator.
 */
class Chip8RepositoryImpl(
    override val memory: Memory,
    override val display: Display,
    override val keyboard: Keyboard,
    override val timers: Timers
) : Chip8Repository {

    /**
     * Aggregated core used to pass shared emulator state to the CPU and instructions.
     */
    private val core = Core(memory, display, keyboard, timers)

    /**
     * CPU implementation that drives execution of CHIP-8 opcodes.
     */
    override val cpu: CPU = CPUImpl(core)

    /**
     * Resets the entire CHIP-8 system to its initial state.
     * Clears memory, display, and timers, and resets the CPU.
     * Also loads the default font set into memory.
     */
    override fun resetSystem() {
        cpu.reset()
        memory.clear()
        display.clear()
        timers.reset()
        memory.loadFont()
    }

    /**
     * Loads a CHIP-8 ROM program into memory starting at address 0x200.
     *
     * @param program The byte array containing the ROM data.
     */
    override fun loadProgram(program: ByteArray) {
        memory.loadProgram(program)
    }

    /**
     * Called every emulation cycle to decrement the delay and sound timers.
     */
    override fun tickTimers() {
        timers.tick()
    }
}
