package com.fakhrirasyids.chemu.domain.instruction

import com.fakhrirasyids.chemu.domain.CPU
import com.fakhrirasyids.chemu.domain.Core
import com.fakhrirasyids.chemu.domain.OPCode

/*
    Author: @fakhrirasyids

    Interface for CHIP-8 instruction implementations.

    Every CHIP-8 instruction MUST IMPLEMENT this interface,
    which defines how it modifies the CPU and Core state.
*/
fun interface Instruction {
    fun execute(core: Core, cpu: CPU, opcode: OPCode)
}