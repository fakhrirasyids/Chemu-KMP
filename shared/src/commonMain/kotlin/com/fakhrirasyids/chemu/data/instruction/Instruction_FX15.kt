package com.fakhrirasyids.chemu.data.instruction

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: FX15 - LD DT, Vx
    Set delay timer = Vx.

    The value stored in register Vx is assigned to the delay timer. 
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_FX15 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        core.timers.delayTimer = cpu.v[opcode.x.toInt()]
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
