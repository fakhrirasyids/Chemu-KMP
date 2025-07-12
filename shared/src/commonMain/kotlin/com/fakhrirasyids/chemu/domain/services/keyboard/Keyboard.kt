package com.fakhrirasyids.chemu.domain.services.keyboard

/**
 * Author: @fakhrirasyids
 *
 * Interface representing the CHIP-8 keyboard input system.
 */
interface Keyboard {

    /**
     * Checks whether the specified CHIP-8 key is currently pressed.
     *
     * @param key Integer from 0 to 15 representing the hex keypad key.
     * @return true if the key is pressed, false otherwise.
     * @throws IllegalArgumentException if key is out of range.
     */
    fun isKeyPressed(key: Int): Boolean

    /**
     * Updates the pressed state of the given key.
     *
     * @param key Integer from 0 to 15 representing the hex keypad key.
     * @param pressed true to mark the key as pressed, false for released.
     * @throws IllegalArgumentException if key is out of range.
     */
    fun setKeyPressed(key: Int, pressed: Boolean)

    /**
     * Retrieves and removes the next key pressed by the user (FIFO order).
     * Used for instructions that block until a key is pressed (`LD Vx, K`).
     *
     * @return The pressed key index (0–15), or null if no key is pressed.
     */
    fun pollKey(): Int?

    /**
     * Returns the next pressed key without removing it from the queue.
     *
     * @return The key index (0–15), or null if no key is queued.
     */
    fun peekKey(): Int?
}