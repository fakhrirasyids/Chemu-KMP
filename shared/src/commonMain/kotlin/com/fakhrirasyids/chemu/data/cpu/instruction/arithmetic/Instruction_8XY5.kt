package com.fakhrirasyids.chemu.data.cpu.instruction.arithmetic

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 8XY5 - SUB Vx, Vy
    Set Vx = Vx - Vy, set VF = NOT borrow.

    Subtracts the value of register Vy from register Vx.
    If Vx > Vy, VF (V[0xF]) is set to 1 to indicate NO borrow.
    Otherwise, VF is set to 0 (borrow occurred).
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_8XY5 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        val y = opcode.y.toInt()
        cpu.v[0xF] = if (cpu.v[x] > cpu.v[y]) 1u else 0u
        cpu.v[x] = ((cpu.v[x] - cpu.v[y]) and 0xFFu).toUByte()
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
