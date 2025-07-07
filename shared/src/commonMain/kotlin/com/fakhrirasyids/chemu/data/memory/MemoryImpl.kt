package com.fakhrirasyids.chemu.data.memory

import com.fakhrirasyids.chemu.domain.services.memory.Memory

/*
    Author: @fakhrirasyids

    Memory implementation for CHIP-8 system.

    CHIP-8 has 4096 bytes (4KB) of memory (0x000 to 0xFFF).
    Typically reserves 0x000 to 0x1FF.
    Programs are loaded starting at address 0x200.

    Key concepts:
        - or    = Bitwise OR (combine bytes into a single instruction)
        - shl   = Shift left (moves each bits to the left)
*/
@OptIn(ExperimentalUnsignedTypes::class)
class MemoryImpl : Memory {
    private val ram = UByteArray(MEMORY_SIZE)

    override fun getByte(address: UShort): UByte = ram[address.toInt()]

    override fun setByte(address: UShort, value: UByte) {
        ram[address.toInt()] = value
    }

    /*
        getWord() reads two consecutive bytes and merges them into a 16-bit instruction.
     */
    override fun getWord(address: UShort): UShort {
        val high = getByte(address).toInt()
        val low = getByte((address + 1u).toUShort()).toInt()
        return ((high shl 8) or low).toUShort()
    }

    /*
        Clears all memory to zero
    */
    override fun clear() {
        ram.fill(0u)
    }

    /*
        Load the CHIP-8 fontset into memory starting at 0x000
    */
    override fun loadFont() {
        val fontset = ubyteArrayOf(
            0xF0u, 0x90u, 0x90u, 0x90u, 0xF0u, // 0
            0x20u, 0x60u, 0x20u, 0x20u, 0x70u, // 1
            0xF0u, 0x10u, 0xF0u, 0x80u, 0xF0u, // 2
            0xF0u, 0x10u, 0xF0u, 0x10u, 0xF0u, // 3
            0x90u, 0x90u, 0xF0u, 0x10u, 0x10u, // 4
            0xF0u, 0x80u, 0xF0u, 0x10u, 0xF0u, // 5
            0xF0u, 0x80u, 0xF0u, 0x90u, 0xF0u, // 6
            0xF0u, 0x10u, 0x20u, 0x40u, 0x40u, // 7
            0xF0u, 0x90u, 0xF0u, 0x90u, 0xF0u, // 8
            0xF0u, 0x90u, 0xF0u, 0x10u, 0xF0u, // 9
            0xF0u, 0x90u, 0xF0u, 0x90u, 0x90u, // A
            0xE0u, 0x90u, 0xE0u, 0x90u, 0xE0u, // B
            0xF0u, 0x80u, 0x80u, 0x80u, 0xF0u, // C
            0xE0u, 0x90u, 0x90u, 0x90u, 0xE0u, // D
            0xF0u, 0x80u, 0xF0u, 0x80u, 0xF0u, // E
            0xF0u, 0x80u, 0xF0u, 0x80u, 0x80u  // F
        )

        fontset.forEachIndexed { i, byte ->
            setByte(i.toUShort(), byte)
        }
    }

    /*
        Load CHIP-8 program into memory starting at 0x200
    */
    override fun loadProgram(program: ByteArray) {
        val start = 0x200
        program.forEachIndexed { i, byte ->
            ram[start + i] = byte.toUByte()
        }
    }

    companion object {
        private const val MEMORY_SIZE = 4096
    }
}
