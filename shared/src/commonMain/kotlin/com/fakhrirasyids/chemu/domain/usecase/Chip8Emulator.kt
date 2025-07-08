package com.fakhrirasyids.chemu.domain.usecase

import com.fakhrirasyids.chemu.data.instruction.InstructionRegistry
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction
import com.fakhrirasyids.chemu.domain.services.memory.Memory
import com.fakhrirasyids.chemu.domain.services.display.Display
import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.services.timers.Timers

/*
    Author: @fakhrirasyids

    Entry-point class to run CHIP-8 programs.
    Wires up the CPU, memory, and peripherals, and exposes basic methods.
*/
class Chip8Emulator(
    private val memory: Memory,
    private val display: Display,
    private val keyboard: Keyboard,
    private val timers: Timers,
) {
    private val core = Core(memory, display, keyboard, timers)
    private val cpu = CPU(core)

    fun cycle() {
        cpu.cycle()
    }

    fun reset() {
        cpu.reset()
        memory.clear()
        display.clear()
        timers.reset()
        memory.loadFont()
    }

    fun loadProgram(program: ByteArray) {
        memory.loadProgram(program)
    }

    fun tickTimers() {
        timers.tick()
    }

    fun getDisplay(): Display = display
    fun getKeyboard(): Keyboard = keyboard

    fun getCpuState(): CPU = cpu
}
