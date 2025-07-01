package com.fakhrirasyids.chemu.data.instruction.system

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: 00E0 - CLS (Clearing Display)

    Clears the CHIP-8 display. This is typically used at the start of a
    program or game to initialize the screen.
*/
class Instruction_00E0 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        core.display.clear()
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
