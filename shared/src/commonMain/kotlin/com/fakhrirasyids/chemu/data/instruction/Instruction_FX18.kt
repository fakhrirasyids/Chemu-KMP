package com.fakhrirasyids.chemu.data.instruction

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: FX18 - LD ST, Vx
    Set sound timer = Vx.

    This instruction sets the value of the sound timer (ST) to the value stored in register Vx.
    While ST is greater than 0, the CHIP-8 buzzer is typically activated (on original hardware).
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_FX18 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        core.timers.soundTimer = cpu.v[opcode.x.toInt()]
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
