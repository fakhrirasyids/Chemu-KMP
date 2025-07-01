package com.fakhrirasyids.chemu.data.instruction

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 7XKK - ADD Vx, byte
    Set Vx = Vx + kk.

    Adds the 8-bit constant kk to the value of register Vx, and stores the result in Vx.
    This operation does not affect the carry flag (VF). Only the lower 8 bits are kept.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_7XKK : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        cpu.v[x] = ((cpu.v[x] + opcode.kk) and 0xFFu).toUByte()
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
