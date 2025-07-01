package com.fakhrirasyids.chemu.data.instruction

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: Cxkk - RND Vx, byte
    Set Vx = random byte AND kk.

    The interpreter generates a random number from 0 to 255,
    performs a bitwise AND with the immediate kk value, and stores
    the result in register Vx.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_CXKK : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        val rnd = (0..255).random().toUByte()
        cpu.v[x] = rnd and opcode.kk
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
