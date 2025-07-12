package com.fakhrirasyids.chemu.data.cpu.instruction.arithmetic

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 8XY1 - OR Vx, Vy
    Set Vx = Vx OR Vy.

    Performs a bitwise OR operation on the values of Vx and Vy,
    then stores the result in Vx. The OR operation compares each
    bit of the two operands; if either bit is 1, the result is 1.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_8XY1 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        val y = opcode.y.toInt()
        cpu.v[x] = cpu.v[x] or cpu.v[y]
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
