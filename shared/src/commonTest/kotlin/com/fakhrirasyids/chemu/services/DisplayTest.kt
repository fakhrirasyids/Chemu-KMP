package com.fakhrirasyids.chemu.services

import com.fakhrirasyids.chemu.data.cpu.display.DisplayImpl
import com.fakhrirasyids.chemu.domain.services.display.Display
import kotlin.test.*

class DisplayTest {

    private lateinit var display: Display

    @BeforeTest
    fun setup() {
        display = DisplayImpl()
    }

    @Test
    fun test_clear_shouldTurnOffAllPixels() {
        display.drawSprite(0, 0, byteArrayOf(0xFF.toByte()))
        display.clear()

        val pixels = display.getPixels()
        for (x in pixels.indices) {
            for (y in pixels[x].indices) {
                assertFalse(pixels[x][y], "Pixel at [$x][$y] should be OFF after clear()")
            }
        }
    }

    @Test
    fun test_drawSprite_setsPixelsCorrectly() {
        val sprite = byteArrayOf(0b11110000.toByte())
        val collision = display.drawSprite(0, 0, sprite)

        val pixels = display.getPixels()
        assertTrue(pixels[0][0])
        assertTrue(pixels[1][0])
        assertTrue(pixels[2][0])
        assertTrue(pixels[3][0])
        assertFalse(pixels[4][0])

        assertFalse(collision, "No pixel should have been unset, so no collision")
    }

    @Test
    fun test_drawSprite_collisionDetection() {
        val sprite = byteArrayOf(0b11110000.toByte())
        display.drawSprite(0, 0, sprite)
        val collision = display.drawSprite(0, 0, sprite)

        val pixels = display.getPixels()
        assertFalse(pixels[0][0])
        assertFalse(pixels[1][0])
        assertFalse(pixels[2][0])
        assertFalse(pixels[3][0])

        assertTrue(collision, "Pixels were unset, so collision should be true")
    }

    @Test
    fun test_shouldDraw_and_clearDrawFlag() {
        assertFalse(display.shouldDraw())

        display.drawSprite(0, 0, byteArrayOf(0xFF.toByte()))
        assertTrue(display.shouldDraw())

        display.clearDrawFlag()
        assertFalse(display.shouldDraw())
    }
}
