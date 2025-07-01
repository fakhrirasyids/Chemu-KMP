package com.fakhrirasyids.chemu.data.instruction.display

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode
import com.fakhrirasyids.chemu.domain.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: DXYN - DRW Vx, Vy, nibble
    Display n-byte sprite starting at memory location I at (Vx, Vy), set VF = collision.

    Reads n bytes from memory starting at address I.
    These bytes are drawn as sprites at coordinates (Vx, Vy).
    Drawing is done using XOR, and if any screen pixels are flipped from
    set to unset, VF is set to 1 (indicating collision).
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_DXYN : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val x = cpu.v[opcode.x.toInt()].toInt()
        val y = cpu.v[opcode.y.toInt()].toInt()
        val height = opcode.n.toInt()

        val sprite = ByteArray(height) { row ->
            core.memory.getByte((cpu.index + row.toUInt()).toUShort()).toByte()
        }

        val collision = core.display.drawSprite(x, y, sprite)
        cpu.v[0xF] = if (collision) 1u else 0u
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}