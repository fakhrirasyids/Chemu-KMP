package com.fakhrirasyids.chemu.data.cpu

import com.fakhrirasyids.chemu.data.cpu.instruction.InstructionRegistry
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.OPCode

/**
 * Author: @fakhrirasyids
 *
 * Implementation of the CHIP-8 CPU. Responsible for fetching, decoding,
 * and executing instructions, as well as managing registers and stack.
 *
 * This implementation assumes the use of an external [Core] to access memory, timers, and other components.
 */
@OptIn(ExperimentalUnsignedTypes::class)
class CPUImpl(
    private val core: Core
) : CPU {

    /**
     * General-purpose 8-bit registers (V0 to VF).
     * VF is also used as a flag register by some instructions.
     */
    override var v = UByteArray(16)

    /**
     * Index register (I). Typically used to store memory addresses.
     */
    override var index: UShort = 0u

    /**
     * Program Counter (PC). Holds the address of the next instruction to execute.
     * Initialized to 0x200 as per CHIP-8 convention.
     */
    override var pc: UShort = 0x200u

    /**
     * Stack used for subroutine calls. Holds return addresses.
     */
    override var stack = UShortArray(16)

    /**
     * Stack Pointer (SP). Points to the top of the stack.
     */
    override var sp: UByte = 0u

    /**
     * Stores the last fetched opcode for debugging or disassembly.
     */
    override var lastOpcode: UShort = 0u

    /**
     * If not null, the CPU will pause execution and wait for a key press,
     * storing the key in the specified register index.
     */
    override var waitingForKeyPressRegister: Int? = null

    /**
     * Reference to the delay timer (read-only).
     */
    override val delayTimer: UByte get() = core.timers.delayTimer

    /**
     * Reference to the sound timer (read-only).
     */
    override val soundTimer: UByte get() = core.timers.soundTimer

    /**
     * Executes one cycle of the CHIP-8 CPU:
     *  - Fetches the current opcode from memory.
     *  - Decodes it using [InstructionRegistry].
     *  - Executes the instruction, updating state accordingly.
     *
     * If the CPU is waiting for a key press, this method will do nothing.
     *
     * @throws IllegalStateException if the instruction is not recognized.
     */
    override fun cycle() {
        if (waitingForKeyPressRegister != null) return

        val word = core.memory.getWord(pc)
        val opcode = OPCode(word)
        lastOpcode = opcode.raw

        val instruction = InstructionRegistry.find(opcode)
            ?: error("Unknown instruction: ${opcode.raw.toString(16)} at PC: ${pc.toString(16)}")

        instruction.execute(core, this, opcode)
    }

    /**
     * Resets the CPU state to initial values.
     * Clears registers, stack, and flags. Sets PC to 0x200.
     */
    override fun reset() {
        v.fill(0u)
        index = 0u
        pc = 0x200u
        stack.fill(0u)
        sp = 0u
        waitingForKeyPressRegister = null
    }
}
