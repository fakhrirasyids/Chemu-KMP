package com.fakhrirasyids.chemu.data.cpu.instruction.system

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 2NNN - CALL addr
    Call subroutine at address nnn.

    Stores the current program counter (PC + 2) on the stack,
    increments the stack pointer (SP), and then sets the program counter to nnn.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_2NNN : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.stack[cpu.sp.toInt()] = (cpu.pc + 2u).toUShort()
        cpu.sp++
        cpu.pc = opcode.nnn
    }
}
