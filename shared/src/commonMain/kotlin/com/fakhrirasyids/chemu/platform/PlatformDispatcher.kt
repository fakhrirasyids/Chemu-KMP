package com.fakhrirasyids.chemu.platform

import kotlinx.coroutines.CoroutineScope

expect class PlatformDispatcher {
    val scope: CoroutineScope
    suspend fun delayFrame()
}
