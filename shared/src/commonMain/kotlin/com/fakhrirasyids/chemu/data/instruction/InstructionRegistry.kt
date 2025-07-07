package com.fakhrirasyids.chemu.data.instruction

import com.fakhrirasyids.chemu.data.instruction.system.*
import com.fakhrirasyids.chemu.data.instruction.control.*
import com.fakhrirasyids.chemu.data.instruction.arithmetic.*
import com.fakhrirasyids.chemu.data.instruction.display.*
import com.fakhrirasyids.chemu.data.instruction.keyboard.*
import com.fakhrirasyids.chemu.data.instruction.memory.*
import com.fakhrirasyids.chemu.data.instruction.random.*
import com.fakhrirasyids.chemu.data.instruction.timers.*
import com.fakhrirasyids.chemu.domain.models.OPCode
import com.fakhrirasyids.chemu.domain.services.instruction.Instruction

object InstructionRegistry {

    private val instructionMap: Map<UShort, Instruction> = mapOf(
        0x00E0u.toUShort() to Instruction_00E0(),
        0x00EEu.toUShort() to Instruction_00EE(),
        0x1000u.toUShort() to Instruction_1NNN(),
        0x2000u.toUShort() to Instruction_2NNN(),
        0x3000u.toUShort() to Instruction_3XKK(),
        0x4000u.toUShort() to Instruction_4XKK(),
        0x5000u.toUShort() to Instruction_5XY0(),
        0x6000u.toUShort() to Instruction_6XKK(),
        0x7000u.toUShort() to Instruction_7XKK(),
        0x8000u.toUShort() to Instruction_8XY0(),
        0x8001u.toUShort() to Instruction_8XY1(),
        0x8002u.toUShort() to Instruction_8XY2(),
        0x8003u.toUShort() to Instruction_8XY3(),
        0x8004u.toUShort() to Instruction_8XY4(),
        0x8005u.toUShort() to Instruction_8XY5(),
        0x8006u.toUShort() to Instruction_8XY6(),
        0x8007u.toUShort() to Instruction_8XY7(),
        0x800Eu.toUShort() to Instruction_8XYE(),
        0x9000u.toUShort() to Instruction_9XY0(),
        0xA000u.toUShort() to Instruction_ANNN(),
        0xB000u.toUShort() to Instruction_BNNN(),
        0xC000u.toUShort() to Instruction_CXKK(),
        0xD000u.toUShort() to Instruction_DXYN(),
        0xE09Eu.toUShort() to Instruction_EX9E(),
        0xE0A1u.toUShort() to Instruction_EXA1(),
        0xF007u.toUShort() to Instruction_FX07(),
        0xF00Au.toUShort() to Instruction_FX0A(),
        0xF015u.toUShort() to Instruction_FX15(),
        0xF018u.toUShort() to Instruction_FX18(),
        0xF01Eu.toUShort() to Instruction_FX1E(),
        0xF029u.toUShort() to Instruction_FX29(),
        0xF033u.toUShort() to Instruction_FX33(),
        0xF055u.toUShort() to Instruction_FX55(),
        0xF065u.toUShort() to Instruction_FX65(),
    )

    fun find(opcode: OPCode): Instruction? {
        instructionMap[opcode.raw]?.let { return it }

        val raw = opcode.raw.toInt()
        return when {
            raw and 0xF00F == 0x8000 -> Instruction_8XY0()
            raw and 0xF00F == 0x8001 -> Instruction_8XY1()
            raw and 0xF00F == 0x8002 -> Instruction_8XY2()
            raw and 0xF00F == 0x8003 -> Instruction_8XY3()
            raw and 0xF00F == 0x8004 -> Instruction_8XY4()
            raw and 0xF00F == 0x8005 -> Instruction_8XY5()
            raw and 0xF00F == 0x8006 -> Instruction_8XY6()
            raw and 0xF00F == 0x8007 -> Instruction_8XY7()
            raw and 0xF00F == 0x800E -> Instruction_8XYE()

            raw and 0xF0FF == 0xE09E -> Instruction_EX9E()
            raw and 0xF0FF == 0xE0A1 -> Instruction_EXA1()

            raw and 0xF0FF == 0xF007 -> Instruction_FX07()
            raw and 0xF0FF == 0xF00A -> Instruction_FX0A()
            raw and 0xF0FF == 0xF015 -> Instruction_FX15()
            raw and 0xF0FF == 0xF018 -> Instruction_FX18()
            raw and 0xF0FF == 0xF01E -> Instruction_FX1E()
            raw and 0xF0FF == 0xF029 -> Instruction_FX29()
            raw and 0xF0FF == 0xF033 -> Instruction_FX33()
            raw and 0xF0FF == 0xF055 -> Instruction_FX55()
            raw and 0xF0FF == 0xF065 -> Instruction_FX65()
            else -> null
        }
    }
}
