package com.fakhrirasyids.chemu.domain.services

/**
 * Author: @fakhrirasyids
 *
 * Interface representing the CHIP-8 Central Processing Unit (CPU).
 *
 * This abstraction defines the internal registers and execution behavior
 * of the virtual CPU, including instruction execution and state reset.
 */
@OptIn(ExperimentalUnsignedTypes::class)
interface CPU {

    /**
     * The general-purpose registers V0 through VF (16 8-bit registers).
     * VF is often used as a flag for certain instructions.
     */
    var v: UByteArray

    /**
     * The 16-bit index register (I), used for memory addressing.
     */
    var index: UShort

    /**
     * The 16-bit program counter (PC), pointing to the next instruction in memory.
     */
    var pc: UShort

    /**
     * The call stack, used for subroutine calls (16 levels deep).
     */
    var stack: UShortArray

    /**
     * The stack pointer (SP), indicating the current stack position.
     */
    var sp: UByte

    /**
     * The last executed raw opcode (for debugging purposes).
     */
    var lastOpcode: UShort

    /**
     * If set, indicates that the CPU is paused and waiting for a key press.
     * The value refers to the register index to store the key into.
     */
    var waitingForKeyPressRegister: Int?

    /**
     * A read-only reference to the delay timer value.
     */
    val delayTimer: UByte

    /**
     * A read-only reference to the sound timer value.
     */
    val soundTimer: UByte

    /**
     * Executes a single fetch-decode-execute cycle.
     *
     * - Fetches the next opcode from memory using [pc].
     * - Decodes the opcode.
     * - Executes the instruction by updating CPU and memory state.
     *
     * This function is a core part of emulation.
     */
    fun cycle()

    /**
     * Resets the CPU to its initial state.
     * Clears all registers, stack, and resets the program counter to 0x200.
     */
    fun reset()
}
