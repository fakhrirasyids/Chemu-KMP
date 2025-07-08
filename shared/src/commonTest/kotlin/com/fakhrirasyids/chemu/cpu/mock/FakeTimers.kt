package com.fakhrirasyids.chemu.cpu.mock

import com.fakhrirasyids.chemu.domain.services.timers.Timers

class FakeTimers() : Timers {
    override var delayTimer: UByte = 0u
    override var soundTimer: UByte = 0u

    var resetCalled = false
    override fun reset() {
        resetCalled = true
    }

    override fun tick() {}
}