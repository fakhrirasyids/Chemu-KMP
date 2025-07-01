package com.fakhrirasyids.chemu.data.instruction.system

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 0NNN - SYS addr

    Originally used to call machine code routines at address NNN.
    This instruction is ignored by modern CHIP-8 interpreters.
    Common practice is to treat it as a NOP (No Operation).
*/
class Instruction_0NNN : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.pc = (cpu.pc + 2u).toUShort() // Ignored, treat as NOP
    }
}