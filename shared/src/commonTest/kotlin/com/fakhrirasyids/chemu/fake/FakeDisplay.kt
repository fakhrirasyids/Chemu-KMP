package com.fakhrirasyids.chemu.fake

import com.fakhrirasyids.chemu.domain.services.display.Display

class FakeDisplay : Display {
    companion object {
        private const val WIDTH = 64
        private const val HEIGHT = 32
    }

    private val pixels = Array(HEIGHT) { BooleanArray(WIDTH) }
    private var drawFlag = false

    override fun clear() {
        for (y in 0 until HEIGHT) {
            for (x in 0 until WIDTH) {
                pixels[y][x] = false
            }
        }
        drawFlag = true
    }

    override fun drawSprite(x: Int, y: Int, sprite: ByteArray): Boolean {
        var collision = false

        for ((rowIndex, byte) in sprite.withIndex()) {
            val rowY = (y + rowIndex) % HEIGHT

            for (bit in 0 until 8) {
                val bitValue = (byte.toInt() shr (7 - bit)) and 1
                if (bitValue == 1) {
                    val colX = (x + bit) % WIDTH
                    if (pixels[rowY][colX]) collision = true
                    pixels[rowY][colX] = pixels[rowY][colX].xor(true)
                }
            }
        }

        drawFlag = true
        return collision
    }

    override fun getPixels(): Array<BooleanArray> {
        return pixels.map { it.copyOf() }.toTypedArray()
    }

    override fun shouldDraw(): Boolean {
        return drawFlag
    }

    override fun clearDrawFlag() {
        drawFlag = false
    }
}
