package com.fakhrirasyids.chemu.data.instruction

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 8XY2 - AND Vx, Vy
    Set Vx = Vx AND Vy.

    Performs a bitwise AND on the values of Vx and Vy,
    then stores the result in Vx.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_8XY2 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        val y = opcode.y.toInt()
        cpu.v[x] = cpu.v[x] and cpu.v[y]
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
