package com.fakhrirasyids.chemu.data.instruction.memory

import com.fakhrirasyids.chemu.domain.usecase.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: FX65 - LD Vx, [I]
    Read registers V0 through Vx from memory starting at location I.

    Reads values from memory starting at the address in register I
    into registers V0 through Vx (inclusive).
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_FX65 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        for (i in 0..opcode.x.toInt()) {
            cpu.v[i] = core.memory.getByte((cpu.index + i.toUInt()).toUShort())
        }
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
