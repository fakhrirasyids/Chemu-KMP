package com.fakhrirasyids.chemu.data.instruction.timers

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: FX07 - LD Vx, DT
    Set Vx = delay timer value.

    The value of the delay timer (DT) is stored into register Vx.
    This is useful for time-based operations or polling timer state.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_FX07 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        cpu.v[opcode.x.toInt()] = core.timers.delayTimer
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
