package com.fakhrirasyids.chemu.data.keyboard

import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard

/*
    Author: @fakhrirasyids

    Keyboard implementation for CHIP-8 system.

    CHIP-8 has 16 keys (0–F) mapped to hex keypad layout.
*/
class KeyboardImpl : Keyboard {
    private val keys = BooleanArray(KEYBOARD_KEY_SIZE)

    override fun isKeyPressed(key: Int): Boolean {
        require(key in 0 until KEYBOARD_KEY_SIZE) { "Invalid key: $key" }
        return keys[key]
    }

    /*
        Pressed keys are stored in a BooleanArray.
     */
    override fun setKeyPressed(key: Int, pressed: Boolean) {
        require(key in 0 until KEYBOARD_KEY_SIZE) { "Invalid key: $key" }
        keys[key] = pressed
    }

    /*
        waitForKeyPress() returns the first key currently pressed
    */
    override fun waitForKeyPress(): Int {
        return keys.indexOfFirst { it }
    }

    companion object {
        private const val KEYBOARD_KEY_SIZE = 16
    }
}
