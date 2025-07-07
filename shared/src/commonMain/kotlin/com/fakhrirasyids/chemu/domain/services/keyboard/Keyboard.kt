package com.fakhrirasyids.chemu.domain.services.keyboard

/*
    Author: @fakhrirasyids

    Keyboard interface for CHIP-8 input.
*/
interface Keyboard {
    fun isKeyPressed(key: Int): Boolean
    fun setKeyPressed(key: Int, pressed: Boolean)
    fun waitForKeyPress(): Int
}