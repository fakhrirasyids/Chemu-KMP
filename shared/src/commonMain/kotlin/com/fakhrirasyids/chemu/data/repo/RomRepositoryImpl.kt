package com.fakhrirasyids.chemu.data.repo

import com.fakhrirasyids.chemu.SharedRes
import com.fakhrirasyids.chemu.domain.repo.RomRepository
import com.fakhrirasyids.chemu.utils.readBytes
import dev.icerock.moko.resources.FileResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

/**
 * Author: @fakhrirasyids
 *
 * Implementation of [RomRepository] that provides access to bundled CHIP-8 ROM files
 * stored as Moko Resources.
 */
class RomRepositoryImpl : RomRepository {

    /**
     * Map of ROM file names to their corresponding [FileResource] entries.
     * Keys represent displayable ROM names used in UI or logic.
     */
    private val romMap: Map<String, FileResource> = mapOf(
        "pong.ch8" to SharedRes.files.pong_ch8,
        "airplane.ch8" to SharedRes.files.airplane_ch8,
        "puzzle.ch8" to SharedRes.files.puzzle_ch8
    )

    /**
     * Returns the list of available ROM names.
     *
     * @return A list of ROM file names (e.g., "pong.ch8").
     */
    override suspend fun getAllRomNames(): List<String> = withContext(Dispatchers.Default) {
        romMap.keys.toList()
    }

    /**
     * Loads a ROM's binary contents by name.
     *
     * @param name The ROM file name (must match a key in [romMap]).
     * @return The byte array containing the ROM's binary data.
     * @throws IllegalArgumentException if the ROM name is not found.
     */
    override suspend fun loadRomByName(name: String): ByteArray = withContext(Dispatchers.IO) {
        val resource = romMap[name] ?: error("ROM file '$name' not found")
        resource.readBytes()
    }
}
