package com.fakhrirasyids.chemu.data.cpu.instruction.memory

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

/*
    Author: @fakhrirasyids

    Instruction: FX33 - LD B, Vx
    Store Binary-Coded Decimal (BCD) representation of Vx in memory locations I, I+1, and I+2.

    Converts the value of Vx into its BCD format:
        - The hundreds digit is stored at memory[I]
        - The tens digit is stored at memory[I + 1]
        - The ones digit is stored at memory[I + 2]
*/
@OptIn(ExperimentalUnsignedTypes::class)
class Instruction_FX33 : Instruction {
    override fun execute(core: Core, cpu: CPU, opcode: OPCode) {
        val value = cpu.v[opcode.x.toInt()].toInt()
        core.memory.setByte(cpu.index, (value / 100).toUByte())                         // Hundreds
        core.memory.setByte((cpu.index + 1u).toUShort(), ((value / 10) % 10).toUByte()) // Tens
        core.memory.setByte((cpu.index + 2u).toUShort(), (value % 10).toUByte())        // Ones
        cpu.pc = (cpu.pc + 2u).toUShort()
    }
}
