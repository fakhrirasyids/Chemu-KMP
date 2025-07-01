package com.fakhrirasyids.chemu.data.instruction

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: FX1E - ADD I, Vx

    Adds the value in register Vx to the index register I.
    The result is stored back in I.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_FX1E : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.index = (cpu.index + cpu.v[opcode.x.toInt()].toUInt()).toUShort()
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
