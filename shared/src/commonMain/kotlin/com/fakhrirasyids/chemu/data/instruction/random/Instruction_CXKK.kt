package com.fakhrirasyids.chemu.data.instruction.random

import com.fakhrirasyids.chemu.domain.usecase.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: Cxkk - RND Vx, byte
    Set Vx = random byte AND kk.

    Generates a random number from 0 to 255,
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