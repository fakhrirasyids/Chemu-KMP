package com.fakhrirasyids.chemu.data.display

import com.fakhrirasyids.chemu.domain.display.Display

/*
    Author: @fakhrirasyids

    Display implementation for CHIP-8.

    The display resolution is 64x32 pixels.
    Drawing uses XOR logic, and returns true if a collision occurred.

    Key concepts:
        - sprite = An array of bytes (each byte represents 1 row of 8 horizontal pixels)
        - row = The row number (0 to sprite.size-1)
        - byte = The 8-pixel pattern for that row (each bit 1 pixel)
*/
class DisplayImpl : Display {
    private val pixels = Array(DISPLAY_WIDTH) { BooleanArray(DISPLAY_HEIGHT) }

    override fun clear() {
        for (x in 0 until DISPLAY_WIDTH) {
            for (y in 0 until DISPLAY_HEIGHT) {
                pixels[x][y] = false
            }
        }
    }

    override fun getPixels(): Array<BooleanArray> = pixels

    /*
        Pixels are stored in the 2D Boolean array, and the
        drawSprite() uses XOR drawing and detects collision.
     */
    override fun drawSprite(x: Int, y: Int, sprite: ByteArray): Boolean {
        var collision = false

        sprite.forEachIndexed { row, byte ->

            // Turns byte to unsigned 8-bit value so it will work correctly with the pixels.
            val bits = byte.toInt() and 0xFF

            repeat(8) { bit ->
                val px = (x + bit) % DISPLAY_WIDTH
                val py = (y + row) % DISPLAY_HEIGHT

                val shouldDraw = ((bits shr (7 - bit)) and 1) == 1
                if (shouldDraw) {
                    if (pixels[px][py]) collision = true
                    pixels[px][py] = pixels[px][py].not()
                }
            }
        }

        return collision
    }

    companion object {
        private const val DISPLAY_WIDTH = 64
        private const val DISPLAY_HEIGHT = 32
    }
}
