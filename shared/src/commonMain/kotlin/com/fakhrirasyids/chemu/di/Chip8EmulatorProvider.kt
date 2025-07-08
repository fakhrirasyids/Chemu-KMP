package com.fakhrirasyids.chemu.di

import com.fakhrirasyids.chemu.data.display.DisplayImpl
import com.fakhrirasyids.chemu.data.keyboard.KeyboardImpl
import com.fakhrirasyids.chemu.data.memory.MemoryImpl
import com.fakhrirasyids.chemu.data.timers.TimersImpl
import com.fakhrirasyids.chemu.domain.services.display.Display
import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard
import com.fakhrirasyids.chemu.domain.services.memory.Memory
import com.fakhrirasyids.chemu.domain.services.timers.Timers
import com.fakhrirasyids.chemu.domain.usecase.Chip8Emulator

/*
    Author: @fakhrirasyids

    Manual DI.
*/
object Chip8EmulatorProvider {
    fun provide(): Chip8Emulator {
        val memory: Memory = MemoryImpl()
        val display: Display = DisplayImpl()
        val keyboard: Keyboard = KeyboardImpl()
        val timers: Timers = TimersImpl()

        return Chip8Emulator(
            memory = memory,
            display = display,
            keyboard = keyboard,
            timers = timers
        )
    }
}
