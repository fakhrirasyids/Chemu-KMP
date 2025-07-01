package com.fakhrirasyids.chemu.domain.keyboard

/*
    Author: @fakhrirasyids

    Keyboard interface for CHIP-8 input.

    CHIP-8 has 16 keys (0–F) mapped to hex keypad layout.
*/
interface Keyboard {
    fun isKeyPressed(key: Int): Boolean
    fun waitForKeyPress(): Int
}