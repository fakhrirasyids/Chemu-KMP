package com.fakhrirasyids.chemu.data.instruction.arithmetic

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 6XKK - LD Vx, byte
    Set Vx = kk.

    Stores the 8-bit constant kk into register Vx.
    This is typically used to initialize or reset register values.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_6XKK : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.v[opcode.x.toInt()] = opcode.kk
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
