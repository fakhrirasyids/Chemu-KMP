package com.fakhrirasyids.chemu.utils

import android.content.Context
import dev.icerock.moko.resources.FileResource

/**
 * Global Android application context used to resolve [FileResource] assets.
 *
 * Must be initialized via [initAndroidContext] during app startup
 * (e.g., inside `Application.onCreate()`).
 */
lateinit var androidAppContext: Context

/**
 * Initializes the global [androidAppContext] used for accessing resources.
 *
 * @param context The application-level [Context].
 */
fun initAndroidContext(context: Context) {
    androidAppContext = context
}

/**
 * Android-specific implementation of [FileResource.readBytes].
 *
 * Reads the raw byte content of a [FileResource] using the global [androidAppContext].
 *
 * @receiver The [FileResource] to be read.
 * @return The contents of the raw resource as a [ByteArray].
 *
 * @throws IllegalStateException if [initAndroidContext] has not been called prior.
 */
actual fun FileResource.readBytes(): ByteArray {
    return androidAppContext.resources.openRawResource(this.rawResId).use {
        it.readBytes()
    }
}