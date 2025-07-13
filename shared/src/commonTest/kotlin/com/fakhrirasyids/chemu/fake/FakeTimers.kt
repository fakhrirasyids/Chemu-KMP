package com.fakhrirasyids.chemu.fake

import com.fakhrirasyids.chemu.domain.services.timers.Timers

class FakeTimers : Timers {
    override var delayTimer: UByte = 0u
    override var soundTimer: UByte = 0u

    override fun tick() {
        if (delayTimer > 0u) delayTimer--
        if (soundTimer > 0u) soundTimer--
    }

    override fun reset() {
        delayTimer = 0u
        soundTimer = 0u
    }
}
