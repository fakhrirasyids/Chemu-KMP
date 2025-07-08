package com.fakhrirasyids.chemu.domain.services.instruction

import com.fakhrirasyids.chemu.domain.usecase.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode

/*
    Author: @fakhrirasyids

    Interface for CHIP-8 instruction implementations.

    Every CHIP-8 instruction MUST IMPLEMENT this interface,
    which defines how it modifies the CPU and Core state.
*/
fun interface Instruction {
    fun execute(core: Core, cpu: CPU, opcode: OPCode)
}