package com.fakhrirasyids.chemu.data.timers

import com.fakhrirasyids.chemu.domain.timers.Timers

/*
    Author: @fakhrirasyids

    Timers implementation for CHIP-8 system.

    CHIP-8 has two timers that both count down at 60Hz:
        - delayTimer = used for timing events
        - soundTimer = buzzer beeps when > 0
*/
class TimersImpl : Timers {
    override var delayTimer: UByte = 0u
    override var soundTimer: UByte = 0u

    fun tick() {
        if (delayTimer > 0u) delayTimer--
        if (soundTimer > 0u) soundTimer--
    }
}
