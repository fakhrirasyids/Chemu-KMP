package com.fakhrirasyids.chemu.domain.models

import com.fakhrirasyids.chemu.domain.services.display.Display
import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard
import com.fakhrirasyids.chemu.domain.services.memory.Memory
import com.fakhrirasyids.chemu.domain.services.timers.Timers

/**
 * Author: @fakhrirasyids
 *
 * Central abstraction representing the CHIP-8 hardware system.
 *
 * This class wires together the four major CHIP-8 components:
 * - [Memory]: Holds program instructions, fontset, and runtime data.
 * - [Display]: 64x32 pixel display using XOR-based drawing and collision detection.
 * - [Keyboard]: 16-key hexadecimal input.
 * - [Timers]: Delay and sound timers, ticking at 60Hz.
 *
 * The [Core] object is passed to the [CPU] and instruction handlers so they can
 * read/write memory, draw to the screen, poll input, or update timers
 * without directly depending on platform-specific implementations.
 */
class Core(
    val memory: Memory,
    val display: Display,
    val keyboard: Keyboard,
    val timers: Timers
)
