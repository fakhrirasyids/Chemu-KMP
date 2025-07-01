package com.fakhrirasyids.chemu.data.instruction.system

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 1NNN - JP addr (Jump)
    Sets the Program Counter (PC) to nnn.

    This is a fundamental control flow instruction used to move execution.
*/
class Instruction_1NNN : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.pc = opcode.nnn
    }
}
