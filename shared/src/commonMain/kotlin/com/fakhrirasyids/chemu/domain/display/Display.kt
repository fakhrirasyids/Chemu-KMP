package com.fakhrirasyids.chemu.domain.display

/*
    Author: @fakhrirasyids

    Display interface for CHIP-8 screen.
*/
interface Display {
    fun clear()
    fun drawSprite(x: Int, y: Int, sprite: ByteArray): Boolean
    fun getPixels(): Array<BooleanArray>
}