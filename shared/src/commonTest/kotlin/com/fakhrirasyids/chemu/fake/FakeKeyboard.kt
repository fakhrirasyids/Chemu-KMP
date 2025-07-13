package com.fakhrirasyids.chemu.fake

import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard

class FakeKeyboard : Keyboard {

    private val keys = BooleanArray(KEYBOARD_KEY_SIZE)
    private val keyQueue = ArrayDeque<Int>()

    override fun isKeyPressed(key: Int): Boolean {
        return keys[key]
    }

    override fun setKeyPressed(key: Int, pressed: Boolean) {
        keys[key] = pressed
        if (pressed && key !in keyQueue) {
            keyQueue.addLast(key)
        }
    }

    override fun pollKey(): Int? {
        return keyQueue.removeFirstOrNull()
    }

    override fun peekKey(): Int? {
        return keyQueue.firstOrNull()
    }

    companion object {
        private const val KEYBOARD_KEY_SIZE = 16
    }
}
