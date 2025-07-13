package com.fakhrirasyids.chemu.fake

import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.services.display.Display
import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard
import com.fakhrirasyids.chemu.domain.services.memory.Memory
import com.fakhrirasyids.chemu.domain.services.timers.Timers

fun createFakeCore(
    memory: Memory = FakeMemory(),
    display: Display = FakeDisplay(),
    keyboard: Keyboard = FakeKeyboard(),
    timers: Timers = FakeTimers()
): Core {
    return Core(
        memory = memory,
        display = display,
        keyboard = keyboard,
        timers = timers
    )
}
