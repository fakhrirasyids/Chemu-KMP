package com.fakhrirasyids.chemu.data.instruction.arithmetic

import com.fakhrirasyids.chemu.domain.usecase.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 8XY0 - LD Vx, Vy
    Set Vx = Vy.

    Copies the value of register Vy into register Vx.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_8XY0 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.v[opcode.x.toInt()] = cpu.v[opcode.y.toInt()]
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
