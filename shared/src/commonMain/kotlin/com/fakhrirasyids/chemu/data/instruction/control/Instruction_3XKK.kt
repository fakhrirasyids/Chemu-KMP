package com.fakhrirasyids.chemu.data.instruction.control

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 3XKK - SE Vx, byte
    Skip the next instruction if Vx == kk

    Compares the value in register Vx with the immediate byte kk.
    If equal, increment PC by 4 (skipping the next instruction).
    Otherwise, increment PC by 2 (continue as normal).
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_3XKK : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.pc = (cpu.pc + (if (cpu.v[opcode.x.toInt()] == opcode.kk) 4u else 2u)).toUShort()
    }
}
