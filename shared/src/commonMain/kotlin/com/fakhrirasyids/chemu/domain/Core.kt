package com.fakhrirasyids.chemu.domain

import com.fakhrirasyids.chemu.domain.display.Display
import com.fakhrirasyids.chemu.domain.keyboard.Keyboard
import com.fakhrirasyids.chemu.domain.memory.Memory
import com.fakhrirasyids.chemu.domain.timers.Timers

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
