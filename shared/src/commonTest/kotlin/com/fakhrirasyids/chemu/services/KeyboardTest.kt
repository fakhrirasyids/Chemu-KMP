package com.fakhrirasyids.chemu.services

import com.fakhrirasyids.chemu.data.cpu.keyboard.KeyboardImpl
import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard
import kotlin.test.*

class KeyboardTest {

    private lateinit var keyboard: Keyboard

    @BeforeTest
    fun setup() {
        keyboard = KeyboardImpl()
    }

    @Test
    fun test_setAndIsKeyPressed() {
        keyboard.setKeyPressed(5, true)
        assertTrue(keyboard.isKeyPressed(5))

        keyboard.setKeyPressed(5, false)
        assertFalse(keyboard.isKeyPressed(5))
    }

    @Test
    fun test_pollKey_returnsAndRemovesKey() {
        keyboard.setKeyPressed(3, true)
        keyboard.setKeyPressed(7, true)

        assertEquals(3, keyboard.pollKey())
        assertEquals(7, keyboard.pollKey())
        assertNull(keyboard.pollKey())
    }

    @Test
    fun test_peekKey_doesNotRemoveKey() {
        keyboard.setKeyPressed(4, true)

        assertEquals(4, keyboard.peekKey())
        assertEquals(4, keyboard.peekKey())
        assertEquals(4, keyboard.pollKey())
        assertNull(keyboard.peekKey())
    }

    @Test
    fun test_invalidKeyThrowsException() {
        assertFailsWith<IllegalArgumentException> { keyboard.setKeyPressed(-1, true) }
        assertFailsWith<IllegalArgumentException> { keyboard.setKeyPressed(16, true) }
        assertFailsWith<IllegalArgumentException> { keyboard.isKeyPressed(20) }
    }

    @Test
    fun test_pressSameKeyTwice_addsOnlyOnce() {
        keyboard.setKeyPressed(1, true)
        keyboard.setKeyPressed(1, true)
        assertEquals(1, keyboard.pollKey())
        assertNull(keyboard.pollKey())
    }
}
