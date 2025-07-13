package com.fakhrirasyids.chemu.services

import com.fakhrirasyids.chemu.data.cpu.memory.MemoryImpl
import com.fakhrirasyids.chemu.domain.services.memory.Memory
import kotlin.test.*

class MemoryTest {

    private lateinit var memory: Memory

    @BeforeTest
    fun setup() {
        memory = MemoryImpl()
    }

    @Test
    fun test_setByte_and_getByte() {
        memory.setByte(0x300u, 0xABu)
        val value = memory.getByte(0x300u)
        assertEquals(0xABu, value)
    }

    @Test
    fun test_getWord_combinesTwoBytesCorrectly() {
        memory.setByte(0x400u, 0x12u)
        memory.setByte(0x401u, 0x34u)
        val word = memory.getWord(0x400u)
        assertEquals(0x1234u, word)
    }

    @Test
    fun test_loadProgram_startsAt0x200() {
        val program = byteArrayOf(0x60.toByte(), 0x0A, 0x61.toByte(), 0x05)
        memory.loadProgram(program)

        assertEquals(0x60u, memory.getByte(0x200u))
        assertEquals(0x0Au, memory.getByte(0x201u))
        assertEquals(0x61u, memory.getByte(0x202u))
        assertEquals(0x05u, memory.getByte(0x203u))
    }

    @Test
    fun test_clear_emptiesMemory() {
        memory.setByte(0x123u, 0x55u)
        memory.setByte(0x124u, 0xAAu)

        memory.clear()

        assertEquals(0x00u, memory.getByte(0x123u))
        assertEquals(0x00u, memory.getByte(0x124u))
    }

    @Test
    fun test_loadFont_populatesFontMemory() {
        memory.clear()
        memory.loadFont()

        val firstChar = (0x00u..0x04u).map { memory.getByte(it.toUShort()) }
        assertTrue(firstChar.all { it != 0u.toUByte() }, "Font should be non-zero after loadFont()")

        val outOfRange = (0x051u..0x060u).map { memory.getByte(it.toUShort()) }
        assertTrue(outOfRange.all { it == 0u.toUByte() }, "Font should be limited to 0x050")
    }
}
