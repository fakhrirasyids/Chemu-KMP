package com.fakhrirasyids.chemu.data.instruction.control

import com.fakhrirasyids.chemu.domain.usecase.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 9XY0 - SNE Vx, Vy
    Skip next instruction if Vx != Vy.

    The values of Vx and Vy are compared. If they are not equal,
    the program counter (PC) is increased by 4 to skip the next instruction.
    Otherwise, PC is increased by 2 as usual.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_9XY0 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        val y = opcode.y.toInt()
        cpu.pc = (cpu.pc + (if (cpu.v[x] != cpu.v[y]) 4u else 2u)).toUShort()
    }
}
