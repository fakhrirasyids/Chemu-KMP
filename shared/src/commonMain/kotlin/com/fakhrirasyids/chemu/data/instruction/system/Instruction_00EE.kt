package com.fakhrirasyids.chemu.data.instruction.system

import com.fakhrirasyids.chemu.domain.usecase.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 00EE - RET (Return)
    Returns from a subroutine call. This is the counterpart to "2nnn - CALL addr".

    CHIP-8 uses a manual stack for managing function calls (subroutines).
    This instruction allows execution to return to the point after the CALL.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_00EE : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.sp--
        cpu.pc = cpu.stack[cpu.sp.toInt()]
    }
}
