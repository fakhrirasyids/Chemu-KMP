package com.fakhrirasyids.chemu.data.instruction.keyboard

import com.fakhrirasyids.chemu.domain.usecase.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: FX0A - LD Vx, K
    Wait for a key press, store the value of the key in Vx.

    All execution halts until a key is pressed. Once a key is pressed,
    its index (0 to 15) is stored into register Vx.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_FX0A : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val key = core.keyboard.waitForKeyPress()
        cpu.v[opcode.x.toInt()] = key.toUByte()
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
