package com.fakhrirasyids.chemu.data.instruction.arithmetic

import com.fakhrirasyids.chemu.domain.usecase.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 8XY7 - SUBN Vx, Vy
    Set Vx = Vy - Vx, set VF = NOT borrow.

    The values of Vy and Vx are compared. If Vy > Vx, VF is set to 1 (no borrow), otherwise 0.
    Then Vx is assigned the result of Vy - Vx.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_8XY7 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        val y = opcode.y.toInt()
        cpu.v[0xF] = if (cpu.v[y] > cpu.v[x]) 1u else 0u
        cpu.v[x] = ((cpu.v[y] - cpu.v[x]) and 0xFFu).toUByte()
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
