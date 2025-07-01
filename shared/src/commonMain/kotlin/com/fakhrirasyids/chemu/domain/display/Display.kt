package com.fakhrirasyids.chemu.domain.display

/*
    Author: @fakhrirasyids

    Display interface for CHIP-8 screen.

    The display resolution is 64x32 pixels, and each pixel is represented by a boolean.
*/
interface Display {
    fun clear()
    fun drawSprite(x: Int, y: Int, sprite: ByteArray): Boolean
    fun getPixels(): Array<BooleanArray>
}