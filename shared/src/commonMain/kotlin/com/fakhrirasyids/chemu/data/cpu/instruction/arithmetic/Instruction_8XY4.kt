package com.fakhrirasyids.chemu.data.cpu.instruction.arithmetic

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction8XY4 - ADD Vx, Vy
    Set Vx = Vx + Vy, set VF = carry.

    Adds the values of Vx and Vy. If the result is greater than 255 (8 bits),
    then VF (V[0xF]) is set to 1 to indicate a carry occurred. Otherwise, VF is set to 0.
    Only the lower 8 bits of the result are stored back in Vx.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_8XY4 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        val y = opcode.y.toInt()
        val sum = cpu.v[x] + cpu.v[y]
        cpu.v[0xF] = if (sum > 0xFFu) 1u else 0u
        cpu.v[x] = (sum and 0xFFu).toUByte()
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
