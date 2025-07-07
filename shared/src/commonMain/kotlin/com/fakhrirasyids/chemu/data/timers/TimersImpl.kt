package com.fakhrirasyids.chemu.data.timers

import com.fakhrirasyids.chemu.domain.services.timers.Timers

/*
    Author: @fakhrirasyids

    Timers implementation for CHIP-8 system.

    CHIP-8 has two timers that both count down at 60Hz:
        - delayTimer = used for timing events
        - soundTimer = buzzer beeps when > 0
*/
class TimersImpl : Timers {
    var delayTimer: UByte = 0u
    var soundTimer: UByte = 0u

    override fun tick() {
        if (delayTimer > 0u) delayTimer--
        if (soundTimer > 0u) soundTimer--
    }

    override fun reset() {
        delayTimer = 0u
        soundTimer = 0u
    }
}
