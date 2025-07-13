package com.fakhrirasyids.chemu.services

import com.fakhrirasyids.chemu.data.cpu.timers.TimersImpl
import com.fakhrirasyids.chemu.domain.services.timers.Timers
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TimersTest {

    private lateinit var timers: Timers

    @BeforeTest
    fun setup() {
        timers = TimersImpl()
    }

    @Test
    fun test_initialTimers_shouldBeZero() {
        assertEquals(0u, timers.delayTimer)
        assertEquals(0u, timers.soundTimer)
    }

    @Test
    fun test_tick_decrementsTimers_ifGreaterThanZero() {
        timers.delayTimer = 5u
        timers.soundTimer = 3u

        timers.tick()
        assertEquals(4u, timers.delayTimer)
        assertEquals(2u, timers.soundTimer)

        timers.tick()
        assertEquals(3u, timers.delayTimer)
        assertEquals(1u, timers.soundTimer)

        timers.tick()
        assertEquals(2u, timers.delayTimer)
        assertEquals(0u, timers.soundTimer)

        timers.tick()
        assertEquals(1u, timers.delayTimer)
        assertEquals(0u, timers.soundTimer)
    }

    @Test
    fun test_reset_setsTimersToZero() {
        timers.delayTimer = 123u
        timers.soundTimer = 45u

        timers.reset()
        assertEquals(0u, timers.delayTimer)
        assertEquals(0u, timers.soundTimer)
    }

    @Test
    fun test_tick_doesNotGoNegative() {
        timers.delayTimer = 1u
        timers.soundTimer = 1u

        timers.tick()
        assertEquals(0u, timers.delayTimer)
        assertEquals(0u, timers.soundTimer)

        timers.tick()
        timers.tick()
        assertEquals(0u, timers.delayTimer)
        assertEquals(0u, timers.soundTimer)
    }
}
