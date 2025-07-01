package com.fakhrirasyids.chemu.data.instruction.arithmetic

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 8XY3 - XOR Vx, Vy
    Set Vx = Vx XOR Vy.

    Performs a bitwise exclusive OR (XOR) on the values of Vx and Vy,
    then stores the result in Vx.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_8XY3 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        val y = opcode.y.toInt()
        cpu.v[x] = cpu.v[x] xor cpu.v[y]
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
