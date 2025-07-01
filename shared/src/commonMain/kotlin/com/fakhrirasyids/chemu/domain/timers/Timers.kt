package com.fakhrirasyids.chemu.domain.timers

/*
    Author: @fakhrirasyids

    Timers interface for CHIP-8 system.

    CHIP-8 has two timers that both count down at 60Hz:
        - delayTimer = used for timing events
        - soundTimer = buzzer beeps when > 0
*/
interface Timers {
    var delayTimer: UByte
    var soundTimer: UByte
}