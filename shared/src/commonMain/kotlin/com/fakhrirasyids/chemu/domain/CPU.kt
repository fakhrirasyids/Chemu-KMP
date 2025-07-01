package com.fakhrirasyids.chemu.domain

import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Class to simulate the CHIP-8 CPU, PC starts at 0x200 (where CHIP-8 programs begin).
    V[15] (VF) is used for carry flag in math and collision logic.

    Key concepts:
        - index =  16-bit address register (for memory addressing
        - pc (Program Counter) = points to current instruction
        - stack = for subroutine (suspending) calls
        - sp (Stack Pointer) = tracks the top of the stack
        - "cycle()" = fetch-decode-execute loop that runs each instruction
*/
@OptIn(ExperimentalUnsignedTypes::class)
class CPU(
    private val core: Core,
    private val instructionMap: Map<UShort, Instruction>
) {
    // 16 general purpose 8-bit registers (V0 to VF)
    val v = UByteArray(16)

    var index: UShort = 0u
    var pc: UShort = 0x200u
    val stack = UShortArray(16)
    var sp: UByte = 0u

    /*
        Emulation cycle:
            1. Fetch the next 2-byte instruction from memory
            2. Decode it into an OPCode object
            3. Use the upper 4 bits (opcode type) to find the right instruction group
            4. Delegate execution to the matched instruction
     */
    fun cycle() {
        val word = core.memory.getWord(pc)
        val opcode = OPCode(word)
        val key = (opcode.raw.toInt() and 0xF000).toUShort()

        val instruction = instructionMap[key]
            ?: error("Unknown instruction: 0x${opcode.raw.toString(16)}")

        instruction.execute(core, this, opcode)
    }
}
