package com.fakhrirasyids.chemu.data.instruction

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 8XYE - SHL Vx {, Vy}
    Set Vx = Vx SHL 1.

    Shift the value in register Vx left by one bit. 
    Store the most significant bit of Vx in VF before the shift.
    Then store the shifted result (Vx * 2) back into Vx.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_8XYE : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        cpu.v[0xF] = ((cpu.v[x].toInt() shr 7) and 0x1).toUByte()
        cpu.v[x] = ((cpu.v[x].toInt() shl 1) and 0xFF).toUByte()
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
