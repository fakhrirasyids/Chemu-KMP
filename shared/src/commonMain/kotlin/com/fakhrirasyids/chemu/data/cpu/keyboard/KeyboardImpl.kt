package com.fakhrirasyids.chemu.data.cpu.keyboard

import com.fakhrirasyids.chemu.domain.services.keyboard.Keyboard

/**
 * Author: @fakhrirasyids
 *
 * CHIP-8 Keyboard implementation.
 *
 * The CHIP-8 virtual machine uses a 16-key hexadecimal keypad (0–F).
 * This class manages the pressed state of each key and supports basic key queueing
 * for use in instructions like `Fx0A` (wait for key press).
 */
class KeyboardImpl : Keyboard {

    // Stores the pressed state of each key (true = pressed, false = not pressed)
    private val keys = BooleanArray(KEYBOARD_KEY_SIZE)

    // Queue of keys that were recently pressed, useful for blocking key instructions (e.g. Fx0A)
    private val keyQueue: ArrayDeque<Int> = ArrayDeque()

    /**
     * Checks if a key is currently pressed.
     *
     * @param key Key index (0–15)
     * @return true if the key is pressed
     * @throws IllegalArgumentException if key is out of bounds
     */
    override fun isKeyPressed(key: Int): Boolean {
        require(key in 0 until KEYBOARD_KEY_SIZE) {
            "Key must be in range 0 to 15"
        }
        return keys[key]
    }

    /**
     * Sets the pressed state of a key.
     *
     * If the key is pressed and not already in the queue, it will be added.
     * This allows the CPU to handle "wait for key" operations via polling.
     *
     * @param key Key index (0–15)
     * @param pressed true if key is pressed, false if released
     * @throws IllegalArgumentException if key is out of bounds
     */
    override fun setKeyPressed(key: Int, pressed: Boolean) {
        require(key in 0 until KEYBOARD_KEY_SIZE) {
            "Key must be in range 0 to 15"
        }
        keys[key] = pressed
        if (pressed && !keyQueue.contains(key)) {
            keyQueue.addLast(key)
        }
    }

    /**
     * Polls and removes the first key from the key press queue.
     * Typically used by instructions that wait for input (e.g. Fx0A).
     *
     * @return The oldest pressed key, or null if queue is empty.
     */
    override fun pollKey(): Int? {
        return if (keyQueue.isNotEmpty()) keyQueue.removeFirst() else null
    }

    /**
     * Returns (but does not remove) the next key in the key queue.
     *
     * @return The next pressed key, or null if queue is empty.
     */
    override fun peekKey(): Int? {
        return keyQueue.firstOrNull()
    }

    companion object {
        private const val KEYBOARD_KEY_SIZE = 16
    }
}
