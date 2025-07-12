package com.fakhrirasyids.chemu.data.cpu.display

import com.fakhrirasyids.chemu.domain.services.display.Display

/**
 * Author: @fakhrirasyids
 *
 * CHIP-8 Display implementation.
 *
 * The CHIP-8 system uses a monochrome 64x32 pixel display.
 * Graphics are drawn using XOR logic, and collision detection is supported.
 *
 * Key Concepts:
 * - Display pixels are stored in a 2D Boolean array [64][32].
 * - A sprite is a vertical array of bytes, where each byte represents 8 horizontal pixels.
 * - Drawing a sprite uses XOR; existing pixels may be flipped.
 * - A collision occurs if drawing a 1 on top of an existing 1 (i.e., pixel turned off).
 *
 * Responsibilities:
 * - Maintain internal pixel state.
 * - Provide functions to clear the screen.
 * - Draw sprites to the screen and report collision.
 * - Manage draw flag for optimizing when to redraw.
 */
class DisplayImpl : Display {
    private var drawFlag = false

    // 64x32 screen buffer: true = pixel on, false = pixel off
    private val pixels = Array(DISPLAY_WIDTH) { BooleanArray(DISPLAY_HEIGHT) }

    /**
     * Clears the display buffer (sets all pixels to false/off).
     */
    override fun clear() {
        for (x in 0 until DISPLAY_WIDTH) {
            for (y in 0 until DISPLAY_HEIGHT) {
                pixels[x][y] = false
            }
        }
    }

    /**
     * Returns the current screen pixel buffer.
     */
    override fun getPixels(): Array<BooleanArray> = pixels

    /**
     * Indicates whether the screen should be redrawn.
     * Useful for skipping unnecessary renders if nothing changed.
     */
    override fun shouldDraw(): Boolean = drawFlag

    /**
     * Resets the draw flag after a frame has been rendered.
     */
    override fun clearDrawFlag() {
        drawFlag = false
    }

    /**
     * Draws a sprite at the given (x, y) coordinate.
     *
     * Each sprite row is one byte (8 bits), and each bit is a horizontal pixel.
     * Drawing uses XOR logic. If a pixel is turned off due to a collision,
     * this function returns true.
     *
     * @param x X position (wrapped if beyond width)
     * @param y Y position (wrapped if beyond height)
     * @param sprite Byte array where each byte represents one row of the sprite
     * @return true if any pixel was turned off (collision), false otherwise
     */
    override fun drawSprite(x: Int, y: Int, sprite: ByteArray): Boolean {
        var collision = false

        sprite.forEachIndexed { row, byte ->
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

        drawFlag = true
        return collision
    }

    companion object {
        private const val DISPLAY_WIDTH = 64
        private const val DISPLAY_HEIGHT = 32
    }
}
