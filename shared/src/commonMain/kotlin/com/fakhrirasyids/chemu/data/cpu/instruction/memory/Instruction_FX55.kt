package com.fakhrirasyids.chemu.data.cpu.instruction.memory

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: FX55 - LD [I], Vx
    Store registers V0 through Vx in memory starting at location I.

    Copies the values of registers V0 through Vx (inclusive)
    into memory starting at the address in register I.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_FX55 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        for (i in 0..opcode.x.toInt()) {
            core.memory.setByte((cpu.index + i.toUInt()).toUShort(), cpu.v[i])
        }
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
