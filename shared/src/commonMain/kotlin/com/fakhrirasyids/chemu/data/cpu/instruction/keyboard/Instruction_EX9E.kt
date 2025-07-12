package com.fakhrirasyids.chemu.data.cpu.instruction.keyboard

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: EX9E - SKP Vx
    Skip next instruction if key with the value of Vx is pressed.

    The instruction checks the keyboard input using the value in register Vx.
    If the key corresponding to Vx is currently being pressed, the program
    counter is increased by 4 (skip next instruction), otherwise by 2.
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_EX9E : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        cpu.pc = (cpu.pc + (if (core.keyboard.isKeyPressed(cpu.v[x].toInt())) 4u else 2u)).toUShort()
    }
}
