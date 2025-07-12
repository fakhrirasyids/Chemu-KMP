package com.fakhrirasyids.chemu.domain.services.timers

/**
 * Author: @fakhrirasyids
 *
 * Interface representing the delay and sound timers in the CHIP-8 system.
 */
interface Timers {

    /**
     * The delay timer (0–255).
     * It is commonly used for game logic timing.
     */
    var delayTimer: UByte

    /**
     * The sound timer (0–255).
     * When greater than 0, a sound is expected to be played.
     */
    var soundTimer: UByte

    /**
     * Ticks both timers by one step.
     *
     * If a timer is greater than 0, it will be decremented by 1.
     */
    fun tick()

    /**
     * Resets both delay and sound timers to 0.
     */
    fun reset()
}
