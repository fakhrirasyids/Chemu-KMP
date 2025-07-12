package com.fakhrirasyids.chemu.data.cpu.timers

import com.fakhrirasyids.chemu.domain.services.timers.Timers

/**
 * Author: @fakhrirasyids
 *
 * Implementation of the CHIP-8 timers.
 */
class TimersImpl : Timers {

    /**
     * Delay timer used for time-based logic (animation, input, etc).
     * Starts at a set value and counts down to 0.
     */
    override var delayTimer: UByte = 0u

    /**
     * Sound timer used to control the buzzer.
     * When greater than 0, a tone should be emitted.
     */
    override var soundTimer: UByte = 0u

    /**
     * Called every tick to decrement both timers.
     * If a timer is already 0, it remains unchanged.
     */
    override fun tick() {
        if (delayTimer > 0u) {
            delayTimer--
        }
        if (soundTimer > 0u) {
            soundTimer--
        }
    }

    /**
     * Resets both timers to zero.
     */
    override fun reset() {
        delayTimer = 0u
        soundTimer = 0u
    }
}
