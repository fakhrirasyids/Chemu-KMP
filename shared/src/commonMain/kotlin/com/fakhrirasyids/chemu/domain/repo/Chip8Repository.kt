package com.fakhrirasyids.chemu.domain.repo

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.services.display.Display
import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard
import com.fakhrirasyids.chemu.domain.services.memory.Memory
import com.fakhrirasyids.chemu.domain.services.timers.Timers

/**
 * Author: @fakhrirasyids
 *
 * Repository interface that provides access to the core CHIP-8 components:
 * CPU, memory, display, keyboard, and timers.
 *
 * This abstraction allows use cases to interact with emulator components without
 * depending on concrete implementations, enabling testability and separation of concerns.
 */
interface Chip8Repository {

    val cpu: CPU
    val display: Display
    val keyboard: Keyboard
    val memory: Memory
    val timers: Timers

    /**
     * Resets the entire CHIP-8 system.
     * This clears memory, display, registers, timers, and resets the program counter.
     */
    fun resetSystem()

    /**
     * Loads a CHIP-8 program (ROM) into memory starting at address 0x200.
     *
     * @param program Byte array representing the program to be loaded.
     */
    fun loadProgram(program: ByteArray)

    /**
     * Decrements the delay and sound timers if they are greater than zero.
     * Should be called at a 60Hz rate.
     */
    fun tickTimers()
}
