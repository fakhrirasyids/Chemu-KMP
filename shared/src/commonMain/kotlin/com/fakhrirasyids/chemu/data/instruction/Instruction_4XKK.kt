package com.fakhrirasyids.chemu.data.instruction

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 4XKK - SNE Vx, byte
    Skip next instruction if Vx != kk.

    Compares register Vx to kk, and if they are not equal,
    it increments the program counter by 4 to skip the next instruction.
    Otherwise, it proceeds normally (adds 2).
*/
class Instruction_4XKK : Instruction {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.pc = (cpu.pc + if (cpu.v[opcode.x.toInt()] != opcode.kk) 4u else 2u).toUShort()
    }
}
