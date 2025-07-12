package com.fakhrirasyids.chemu.domain.services.display

/**
 * Author: @fakhrirasyids
 *
 * Interface representing the CHIP-8 screen display system.
 */
interface Display {

    /**
     * Clears all pixels on the screen.
     * Typically used at startup or after CLS instruction.
     */
    fun clear()

    /**
     * Draws a sprite to the display using XOR logic.
     *
     * @param x The X coordinate (0..63)
     * @param y The Y coordinate (0..31)
     * @param sprite Byte array of sprite rows (each byte = 8 horizontal pixels)
     * @return true if any pixel was unset (collision occurred), false otherwise
     */
    fun drawSprite(x: Int, y: Int, sprite: ByteArray): Boolean

    /**
     * Returns the 2D boolean array representing the current screen pixel state.
     *
     * @return 64x32 boolean matrix (true = pixel on)
     */
    fun getPixels(): Array<BooleanArray>

    /**
     * Whether the screen has been updated since last draw.
     * Used by rendering systems to determine if a repaint is needed.
     */
    fun shouldDraw(): Boolean

    /**
     * Clears the draw flag after rendering is complete.
     */
    fun clearDrawFlag()
}
