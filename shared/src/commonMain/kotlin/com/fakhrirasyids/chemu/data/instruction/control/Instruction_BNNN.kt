package com.fakhrirasyids.chemu.data.instruction.control

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: BNNN - JP V0, addr
    Jump to location nnn + V0.

    This instruction sets the program counter (PC) to the value of nnn
    plus the value stored in register V0. It enables relative jumps 
    that depend on the contents of V0.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_BNNN : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.pc = (opcode.nnn + cpu.v[0].toUInt()).toUShort()
    }
}
