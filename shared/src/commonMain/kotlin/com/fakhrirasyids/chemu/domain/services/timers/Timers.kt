package com.fakhrirasyids.chemu.domain.services.timers

/*
    Author: @fakhrirasyids

    Timers interface for CHIP-8 system.
*/
interface Timers {
    var delayTimer: UByte
    var soundTimer: UByte

    fun tick()
    fun reset()
}