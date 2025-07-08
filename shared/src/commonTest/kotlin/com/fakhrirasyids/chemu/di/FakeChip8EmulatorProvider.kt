package com.fakhrirasyids.chemu.di

import com.fakhrirasyids.chemu.cpu.mock.FakeDisplay
import com.fakhrirasyids.chemu.cpu.mock.FakeKeyboard
import com.fakhrirasyids.chemu.cpu.mock.FakeMemory
import com.fakhrirasyids.chemu.cpu.mock.FakeTimers
import com.fakhrirasyids.chemu.domain.usecase.Chip8Emulator

object FakeChip8EmulatorProvider {
    fun provide(): Pair<Chip8Emulator, Dependencies> {
        val memory = FakeMemory()
        val display = FakeDisplay()
        val keyboard = FakeKeyboard()
        val timers = FakeTimers()

        val emulator = Chip8Emulator(memory, display, keyboard, timers)

        return emulator to Dependencies(memory, display, keyboard, timers)
    }

    data class Dependencies(
        val memory: FakeMemory,
        val display: FakeDisplay,
        val keyboard: FakeKeyboard,
        val timers: FakeTimers
    )
}
