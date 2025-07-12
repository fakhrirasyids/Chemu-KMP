package com.fakhrirasyids.chemu.data.cpu.instruction.memory

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: FX29 - LD F, Vx
    Set I = location of sprite for digit Vx.

    Each digit (0–F) has a predefined 5-byte sprite stored in memory starting at 0x000.
    To locate the sprite, we multiply the digit value by 5.
    This sets the index register I to the memory address of the corresponding sprite.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_FX29 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val digit = cpu.v[opcode.x.toInt()].toInt()
        cpu.index = (digit * 5).toUShort()
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
