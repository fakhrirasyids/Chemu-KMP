package com.fakhrirasyids.chemu.utils

import dev.icerock.moko.resources.FileResource

/**
 * Platform-specific extension function for reading a [FileResource] as a [ByteArray].
 *
 * This `expect` function is implemented separately for each platform.
 *
 * It allows binary ROM files or other raw assets to be accessed as byte arrays,
 * which is required for CHIP-8 emulator to load and run programs.
 *
 * @receiver The [FileResource] to read.
 * @return The content of the resource as a byte array.
 *
 * @throws IllegalStateException if the resource cannot be found or read.
 */
expect fun FileResource.readBytes(): ByteArray
