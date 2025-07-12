package com.fakhrirasyids.chemu.di

import com.fakhrirasyids.chemu.data.cpu.display.DisplayImpl
import com.fakhrirasyids.chemu.data.cpu.keyboard.KeyboardImpl
import com.fakhrirasyids.chemu.data.cpu.memory.MemoryImpl
import com.fakhrirasyids.chemu.data.cpu.timers.TimersImpl
import com.fakhrirasyids.chemu.data.repo.Chip8RepositoryImpl
import com.fakhrirasyids.chemu.data.repo.RomRepositoryImpl
import com.fakhrirasyids.chemu.domain.repo.Chip8Repository
import com.fakhrirasyids.chemu.domain.repo.RomRepository
import com.fakhrirasyids.chemu.domain.services.display.Display
import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard
import com.fakhrirasyids.chemu.domain.services.memory.Memory
import com.fakhrirasyids.chemu.domain.services.timers.Timers

/**
 * Author: @fakhrirasyids
 *
 * Provides singleton instances of repositories used in the CHIP-8 emulator.
 * Acts as a simple manual dependency injector for core services.
 */
object RepositoryProvider {

    fun provideChip8Repository(): Chip8Repository {
        val memory: Memory = MemoryImpl()
        val display: Display = DisplayImpl()
        val keyboard: Keyboard = KeyboardImpl()
        val timers: Timers = TimersImpl()

        return Chip8RepositoryImpl(
            memory = memory,
            display = display,
            keyboard = keyboard,
            timers = timers
        )
    }

    fun provideRomRepository(): RomRepository {
        return RomRepositoryImpl()
    }
}
