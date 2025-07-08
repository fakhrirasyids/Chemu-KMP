package com.fakhrirasyids.chemu.data.instruction.control

import com.fakhrirasyids.chemu.domain.usecase.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 5XY0 - SE Vx, Vy
    Skip next instruction if Vx == Vy.

    Compares register Vx to register Vy, and if they are equal,
    it increments the program counter by 4 to skip the next instruction.
    Otherwise, it proceeds normally (adds 2).
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_5XY0 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        val y = opcode.y.toInt()
        cpu.pc = (cpu.pc + if (cpu.v[x] == cpu.v[y]) 4u else 2u).toUShort()
    }
}
