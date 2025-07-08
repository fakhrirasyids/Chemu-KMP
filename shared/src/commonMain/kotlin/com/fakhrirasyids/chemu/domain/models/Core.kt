package com.fakhrirasyids.chemu.domain.models

import com.fakhrirasyids.chemu.domain.services.display.Display
import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard
import com.fakhrirasyids.chemu.domain.services.memory.Memory
import com.fakhrirasyids.chemu.domain.services.timers.Timers

/*
    Author: @fakhrirasyids

    Core abstraction that connects all CHIP-8 hardware components:
        - Memory
        - Display
        - Keyboard
        - Timers

    This allows the CPU and instructions to interact with the system
    without knowing about platform-specific implementations.
*/
class Core(
    val memory: Memory,
    val display: Display,
    val keyboard: Keyboard,
    val timers: Timers
)
