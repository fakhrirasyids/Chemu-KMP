package com.fakhrirasyids.chemu.data.instruction.keyboard

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: EXA1 - SKNP Vx
    Skip next instruction if key with the value of Vx is not pressed.

    This instruction checks the keyboard state.
    If the key stored in Vx is *not* currently pressed,
    the program counter is increased by 4 (skipping the next instruction).
    Otherwise, it proceeds normally to the next instruction (adds 2).
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_EXA1 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = opcode.x.toInt()
        cpu.pc = (cpu.pc + (if (!core.keyboard.isKeyPressed(cpu.v[x].toInt())) 4u else 2u)).toUShort()
    }
}
