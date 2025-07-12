package com.fakhrirasyids.chemu.utils

import dev.icerock.moko.resources.FileResource
import kotlinx.cinterop.*
import platform.Foundation.*
import platform.posix.memcpy

/**
 * iOS-specific implementation of [FileResource.readBytes].
 *
 * Loads the content of a resource file bundled within the app and returns its byte representation.
 *
 * @receiver The [FileResource] to be read.
 * @return The contents of the file as a [ByteArray].
 *
 * @throws IllegalStateException If the file is not found in the bundle or fails to load.
 */
actual fun FileResource.readBytes(): ByteArray {
    val path = NSBundle.mainBundle.pathForResource(this.fileName, null)
        ?: error("ROM file ${this.fileName} not found in bundle")
    val data = NSData.dataWithContentsOfFile(path)
        ?: error("Unable to load data for file: $path")
    return data.toKmpByteArray()
}

/**
 * Converts [NSData] to a Kotlin [ByteArray].
 *
 * Uses native memory operations to copy the raw data into a Kotlin array.
 *
 * @receiver The [NSData] instance representing file content.
 * @return A [ByteArray] containing the same data.
 */
@OptIn(ExperimentalForeignApi::class)
private fun NSData.toKmpByteArray(): ByteArray {
    val size = this.length.toInt()
    val byteArray = ByteArray(size)
    memScoped {
        val bufferPointer = byteArray.refTo(0).getPointer(this)
        memcpy(bufferPointer, this@toKmpByteArray.bytes, this@toKmpByteArray.length)
    }
    return byteArray
}