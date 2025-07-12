package com.fakhrirasyids.chemu.di

import com.fakhrirasyids.chemu.domain.usecase.Chip8EmulatorUseCase
import com.fakhrirasyids.chemu.domain.usecase.RomUseCase

/**
 * Author: @fakhrirasyids
 *
 * Provides instances of domain use cases used by the CHIP-8 emulator.
 * Responsible for wiring use cases with their required repositories.
 */
object UseCaseProvider {

    fun provideChip8EmulatorUseCase(): Chip8EmulatorUseCase {
        val repository = RepositoryProvider.provideChip8Repository()
        return Chip8EmulatorUseCase(repository)
    }

    fun provideRomUseCase(): RomUseCase {
        return RomUseCase(RepositoryProvider.provideRomRepository())
    }
}
