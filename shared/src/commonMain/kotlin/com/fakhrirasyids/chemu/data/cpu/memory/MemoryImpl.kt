package com.fakhrirasyids.chemu.data.cpu.memory

import com.fakhrirasyids.chemu.domain.services.memory.Memory

/**
 * Memory implementation for the CHIP-8 system.
 *
 * The CHIP-8 has a total of 4096 bytes (4KB) of memory, ranging from 0x000 to 0xFFF.
 *
 * - The range 0x000 to 0x1FF is typically reserved (for interpreter, fontset, etc).
 * - Programs are conventionally loaded starting at memory address 0x200 (512 in decimal).
 *
 * Key Concepts:
 * - `getWord()` reads two consecutive bytes and combines them into a 16-bit instruction.
 * - Fontset is hardcoded into the first 80 bytes of memory starting at 0x000.
 *
 * Author: @fakhrirasyids
 */
@OptIn(ExperimentalUnsignedTypes::class)
class MemoryImpl : Memory {

    // 4KB memory (0x000 to 0xFFF)
    private val ram = UByteArray(MEMORY_SIZE)

    /**
     * Reads a single byte at the specified memory address.
     * @param address The memory address to read from.
     * @return The byte value at the given address.
     */
    override fun getByte(address: UShort): UByte = ram[address.toInt()]

    /**
     * Writes a byte to the specified memory address.
     * @param address The memory address to write to.
     * @param value The byte value to write.
     */
    override fun setByte(address: UShort, value: UByte) {
        ram[address.toInt()] = value
    }

    /**
     * Reads a 2-byte word (16 bits) from memory by combining two consecutive bytes.
     * The CHIP-8 stores instructions in big-endian format.
     *
     * @param address The starting address of the word.
     * @return A 16-bit word (UShort) representing the instruction.
     */
    override fun getWord(address: UShort): UShort {
        val high = getByte(address).toInt()
        val low = getByte((address + 1u).toUShort()).toInt()
        return ((high shl 8) or low).toUShort()
    }

    /**
     * Resets all memory bytes to zero.
     */
    override fun clear() {
        ram.fill(0u)
    }

    /**
     * Loads the built-in CHIP-8 fontset into memory starting at 0x000.
     * Each font sprite is 5 bytes tall, and represents hexadecimal digits (0–F).
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

        fontset.forEachIndexed { index, byte ->
            setByte(index.toUShort(), byte)
        }
    }

    /**
     * Loads a CHIP-8 ROM/program into memory starting at address 0x200.
     *
     * @param program The binary data of the ROM to load.
     */
    override fun loadProgram(program: ByteArray) {
        val startAddress = 0x200
        program.forEachIndexed { index, byte ->
            ram[startAddress + index] = byte.toUByte()
        }
    }

    companion object {
        private const val MEMORY_SIZE = 4096
    }
}