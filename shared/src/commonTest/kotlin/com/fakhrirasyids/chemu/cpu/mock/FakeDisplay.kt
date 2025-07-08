package com.fakhrirasyids.chemu.cpu.mock

import com.fakhrirasyids.chemu.domain.services.display.Display

class FakeDisplay : Display {
    var cleared = false
    override fun clear() { cleared = true }
    override fun drawSprite(
        x: Int,
        y: Int,
        sprite: ByteArray
    ): Boolean {
        return true
    }

    override fun getPixels(): Array<BooleanArray> {
        return emptyArray()
    }
}