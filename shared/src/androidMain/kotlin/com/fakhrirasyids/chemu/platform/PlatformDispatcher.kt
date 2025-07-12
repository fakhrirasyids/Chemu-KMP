package com.fakhrirasyids.chemu.platform

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay

actual class PlatformDispatcher {
    actual val scope: CoroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    actual suspend fun delayFrame() {
        delay(16L) // ~60fps
    }
}