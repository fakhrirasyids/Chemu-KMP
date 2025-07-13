package com.fakhrirasyids.chemu.cpu

import com.fakhrirasyids.chemu.data.cpu.CPUImpl
import com.fakhrirasyids.chemu.domain.services.CPU
import com.fakhrirasyids.chemu.fake.createFakeCore
import kotlin.test.*

class CPUTest {

    private lateinit var cpu: CPU
    private lateinit var core: com.fakhrirasyids.chemu.domain.models.Core

    @BeforeTest
    fun setup() {
        core = createFakeCore()
        cpu = CPUImpl(core)
    }

    @Test
    fun test_initialState_afterReset() {
        cpu.reset()

        assertEquals(0x200u, cpu.pc)
        assertEquals(0u, cpu.index)
        assertEquals(0u, cpu.sp)
        assertEquals(0u, cpu.lastOpcode)
        assertNull(cpu.waitingForKeyPressRegister)

        assertTrue(cpu.v.all { it == 0u.toUByte() })
        assertTrue(cpu.stack.all { it == 0u.toUShort() })
    }

    @Test
    fun test_registersCanBeWrittenAndRead() {
        cpu.v[2] = 0x42u
        cpu.index = 0x123u
        cpu.sp = 3u
        cpu.stack[3] = 0x345u
        cpu.lastOpcode = 0xABCDu
        cpu.waitingForKeyPressRegister = 6

        assertEquals(0x42u, cpu.v[2])
        assertEquals(0x123u, cpu.index)
        assertEquals(3u, cpu.sp)
        assertEquals(0x345u, cpu.stack[3])
        assertEquals(0xABCDu, cpu.lastOpcode)
        assertEquals(6, cpu.waitingForKeyPressRegister)
    }

    @Test
    fun test_cycle_executesInstructionAndUpdatesLastOpcode() {
        // Write instruction 0x00E0 (CLS) into memory manually
        val pc = cpu.pc
        core.memory.setByte(pc, 0x00u)
        core.memory.setByte((pc + 1u).toUShort(), 0xE0u)

        cpu.cycle()

        assertEquals(0x00E0u, cpu.lastOpcode)
    }

    @Test
    fun test_cycle_doesNothingWhenWaitingForKeyPress() {
        cpu.waitingForKeyPressRegister = 1

        val pc = cpu.pc
        core.memory.setByte(pc, 0x00u)
        core.memory.setByte((pc + 1u).toUShort(), 0xE0u)

        cpu.cycle()

        // Should skip instruction execution
        assertEquals(0u, cpu.lastOpcode)
    }

    @Test
    fun test_cycle_throwsErrorOnUnknownInstruction() {
        val pc = cpu.pc
        core.memory.setByte(pc, 0xFFu)
        core.memory.setByte((pc + 1u).toUShort(), 0xFFu)

        val exception = assertFailsWith<IllegalStateException> {
            cpu.cycle()
        }

        assertTrue(exception.message!!.contains("Unknown instruction"))
    }

    @Test
    fun test_timersExposeCoreTimers() {
        core.timers.delayTimer = 15u
        core.timers.soundTimer = 7u

        assertEquals(15u, cpu.delayTimer)
        assertEquals(7u, cpu.soundTimer)
    }
}
