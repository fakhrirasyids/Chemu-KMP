package com.fakhrirasyids.chemu.data.cpu.instruction.timers

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: FX07 - LD Vx, DT
    Set Vx = delay timer value.

    The value of the delay timer (DT) is stored into register Vx.
    This is useful for time-based operations or polling timer state.
*/
class Instruction_FX07 : Instruction {
    @OptIn(ExperimentalUnsignedTypes::class)
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val dt = core.timers.delayTimer
        cpu.v[opcode.x.toInt()] = dt
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
