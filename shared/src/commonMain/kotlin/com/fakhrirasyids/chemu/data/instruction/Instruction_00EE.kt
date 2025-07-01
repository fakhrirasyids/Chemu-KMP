package com.fakhrirasyids.chemu.data.instruction

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

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
