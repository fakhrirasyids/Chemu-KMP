package com.fakhrirasyids.chemu.cpu.mock

import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard

class FakeKeyboard : Keyboard {
    override fun isKeyPressed(key: Int): Boolean {
        return true
    }

    override fun setKeyPressed(key: Int, pressed: Boolean) {
    }

    override fun waitForKeyPress(): Int {
        return 1;
    }
}