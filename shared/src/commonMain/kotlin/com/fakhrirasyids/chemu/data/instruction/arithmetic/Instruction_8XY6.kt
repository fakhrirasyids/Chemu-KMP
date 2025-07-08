package com.fakhrirasyids.chemu.data.instruction.arithmetic

import com.fakhrirasyids.chemu.domain.usecase.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 8XY6 - SHR Vx {, Vy}
    Set Vx = Vx >> 1. Set VF to least significant bit prior to shift.

    This instruction shifts the value of Vx right by one (division by 2).
    Before the shift, the least significant bit (LSB) is saved into VF (V[0xF]),
    then the result is stored back into Vx.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_8XY6 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        cpu.v[0xF] = (cpu.v[x].toInt() and 0x1).toUByte()
        cpu.v[x] = (cpu.v[x].toInt() shr 1).toUByte()
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
