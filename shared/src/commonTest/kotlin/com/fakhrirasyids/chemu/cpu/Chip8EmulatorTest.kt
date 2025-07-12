package com.fakhrirasyids.chemu.cpu

import com.fakhrirasyids.chemu.di.FakeChip8EmulatorProvider
import com.fakhrirasyids.chemu.domain.usecase.Chip8EmulatorUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Chip8EmulatorTest {

    private lateinit var emulator: Chip8EmulatorUseCase
    private lateinit var deps: FakeChip8EmulatorProvider.Dependencies

    @BeforeTest
    fun setup() {
        val (instance, dependencies) = FakeChip8EmulatorProvider.provide()
        emulator = instance
        deps = dependencies
    }

    @Test
    fun testLoadProgram_setsMemoryCorrectly() {
        val program = byteArrayOf(0x60.toByte(), 0x0A.toByte()) // 600A → LD V0, 0x0A
        emulator.loadProgram(program)

        val word = deps.memory.getWord(0x200u)
        assertEquals(0x600A.toUShort(), word)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    @Test
    fun testCycle_executesInstruction() {
        emulator.loadProgram(byteArrayOf(0x60.toByte(), 0x0A.toByte()))
        emulator.cycle()

        val v0 = emulator.getCpuState().v[0]
        assertEquals(0x0Au.toUByte(), v0)
    }

    @Test
    fun testReset_clearsEverything() {
        emulator.reset()

        assertTrue(deps.display.cleared)
        assertTrue(deps.timers.resetCalled)
    }
}
