package com.fakhrirasyids.chemu.presentation

/**
 * Author: @fakhrirasyids
 *
 * UI State data class representing the current visible state of the CHIP-8 emulator.
 *
 * This is collected and observed by the UI layer.
 * It includes:
 * - Framebuffer (64x32 pixels)
 * - CPU registers (PC, I, V0–VF)
 * - Opcode currently being executed
 * - Timers (delay & sound)
 * - Stack and debug flag
 *
 * @property pixels   64x32 boolean framebuffer (true = pixel on)
 * @property frameId  Incremented on each draw call to detect changes
 * @property pc       Program counter (address of current instruction)
 * @property i        Index register (I)
 * @property dt       Delay timer value
 * @property st       Sound timer value
 * @property opcode   Last executed opcode
 * @property registers List of values in V0–VF (16 general-purpose registers)
 * @property stack    Current call stack (can be empty)
 * @property debug    Flag indicating whether debug mode is active
 */
data class Chip8UiState(
    val pixels: Array<BooleanArray> = Array(64) { BooleanArray(32) },
    val frameId: Int = 0,
    val pc: Int = 0,
    val i: Int = 0,
    val dt: Int = 0,
    val st: Int = 0,
    val opcode: Int = 0,
    val registers: List<Int> = List(16) { 0 },
    val stack: List<Int> = emptyList(),
    val debug: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Chip8UiState) return false

        return frameId == other.frameId &&
                pc == other.pc &&
                i == other.i &&
                dt == other.dt &&
                st == other.st &&
                opcode == other.opcode &&
                debug == other.debug &&
                registers == other.registers &&
                stack == other.stack &&
                pixels.contentDeepEquals(other.pixels)
    }

    override fun hashCode(): Int {
        var result = frameId
        result = 31 * result + pc
        result = 31 * result + i
        result = 31 * result + dt
        result = 31 * result + st
        result = 31 * result + opcode
        result = 31 * result + debug.hashCode()
        result = 31 * result + pixels.contentDeepHashCode()
        result = 31 * result + registers.hashCode()
        result = 31 * result + stack.hashCode()
        return result
    }
}
