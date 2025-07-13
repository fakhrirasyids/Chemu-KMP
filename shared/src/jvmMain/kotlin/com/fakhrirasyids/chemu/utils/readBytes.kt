package com.fakhrirasyids.chemu.utils

import dev.icerock.moko.resources.FileResource

actual fun FileResource.readBytes(): ByteArray {
    val path = "MR/files/${this.filePath}"
    val stream = Thread.currentThread().contextClassLoader.getResourceAsStream(path)
        ?: error("Cannot find resource: $path")
    return stream.readBytes()
}
