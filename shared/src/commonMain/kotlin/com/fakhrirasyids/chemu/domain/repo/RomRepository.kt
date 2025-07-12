package com.fakhrirasyids.chemu.domain.repo

/**
 * Author: @fakhrirasyids
 *
 * Repository interface for accessing bundled CHIP-8 ROM files.
 *
 * Abstracts the logic of retrieving available ROM file names and loading their contents.
 * This allows different platform-specific implementations.
 * while maintaining a common interface for use cases and ViewModels.
 */
interface RomRepository {

    /**
     * Returns the list of available CHIP-8 ROM file names bundled with the app.
     *
     * @return A list of file names.
     */
    suspend fun getAllRomNames(): List<String>

    /**
     * Loads a CHIP-8 ROM file by its name.
     *
     * @param name The name of the ROM file to load (must match one from [getAllRomNames]).
     * @return The contents of the ROM as a [ByteArray].
     * @throws IllegalArgumentException if the ROM with the given name is not found.
     */
    suspend fun loadRomByName(name: String): ByteArray
}
