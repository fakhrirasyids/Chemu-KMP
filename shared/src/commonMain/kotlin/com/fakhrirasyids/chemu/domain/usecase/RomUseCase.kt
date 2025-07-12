package com.fakhrirasyids.chemu.domain.usecase

import com.fakhrirasyids.chemu.domain.repo.RomRepository

/**
 * Author: @fakhrirasyids
 *
 * Use case responsible for managing CHIP-8 ROM-related operations.
 *
 * Acts as a bridge between the domain logic and the underlying [RomRepository],
 * providing methods to fetch available ROMs and load a specific ROM's content.
 *
 * @property repository The [RomRepository] implementation that handles actual ROM resource access.
 */
class RomUseCase(
    private val repository: RomRepository
) {

    /**
     * Retrieves the list of all available ROM file names bundled with the app.
     *
     * @return A list of ROM names (e.g., "pong.ch8", "airplane.ch8").
     */
    suspend fun getRomList(): List<String> = repository.getAllRomNames()

    /**
     * Loads the content of a ROM file by its name.
     *
     * @param name The name of the ROM file (must match the internal ROM map).
     * @return A [ByteArray] representing the contents of the ROM.
     * @throws IllegalArgumentException if the ROM name is not found.
     */
    suspend fun loadRom(name: String): ByteArray = repository.loadRomByName(name)
}