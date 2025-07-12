package com.fakhrirasyids.chemu.data.cpu.instruction.memory

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: ANNN - LD I, addr
    Set I = nnn.

    Loads the immediate address (nnn) into the index register I.
*/
class Instruction_ANNN : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.index = opcode.nnn
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
