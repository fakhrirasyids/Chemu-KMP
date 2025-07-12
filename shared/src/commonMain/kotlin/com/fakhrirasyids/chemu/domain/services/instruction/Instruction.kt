package com.fakhrirasyids.chemu.domain.services.instruction

import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.domain.models.Core
import com.fakhrirasyids.chemu.domain.models.OPCode

/**
 * Author: @fakhrirasyids
 *
 * Functional interface representing a single CHIP-8 instruction.
 */
fun interface Instruction {

    /**
     * Executes the CHIP-8 instruction logic.
     *
     * @param core The system abstraction (memory, display, keyboard, timers)
     * @param cpu The CPU state (registers, PC, stack, etc.)
     * @param opcode The current 2-byte instruction to execute
     */
    fun execute(core: Core, cpu: CPU, opcode: OPCode)
}
