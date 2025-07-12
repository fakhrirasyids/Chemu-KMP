package com.fakhrirasyids.chemu.platform

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay

actual class PlatformDispatcher {
    actual val scope: CoroutineScope = MainScope()
    actual suspend fun delayFrame() {
        delay(16L)
    }
}