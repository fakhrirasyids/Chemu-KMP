package com.fakhrirasyids.chemu.di

import com.fakhrirasyids.chemu.platform.PlatformDispatcher
import com.fakhrirasyids.chemu.presentation.Chip8ViewModel

/**
 * Author: @fakhrirasyids
 *
 * Provides instances of ViewModels used in the CHIP-8 emulator presentation layer.
 */
object ViewModelProvider {

    fun provideChip8ViewModel(platform: PlatformDispatcher): Chip8ViewModel {
        val chip8UseCase = UseCaseProvider.provideChip8EmulatorUseCase()
        val romUseCase = UseCaseProvider.provideRomUseCase()
        return Chip8ViewModel(chip8UseCase, romUseCase, platform)
    }
}
