package com.fakhrirasyids.chemu.domain.services.memory

/**
 * Author: @fakhrirasyids
 *
 * Interface representing the memory system of the CHIP-8 virtual machine.
 */
interface Memory {

    /**
     * Reads a single byte from memory at the specified address.
     *
     * @param address 12-bit memory address (0x000 to 0xFFF)
     * @return The byte stored at the given address
     */
    fun getByte(address: UShort): UByte

    /**
     * Writes a byte to memory at the specified address.
     *
     * @param address 12-bit memory address (0x000 to 0xFFF)
     * @param value The byte to write
     */
    fun setByte(address: UShort, value: UByte)

    /**
     * Reads a 2-byte word (instruction) from memory starting at the specified address.
     *
     * Combines the byte at `address` (high byte) and `address + 1` (low byte) into a `UShort`.
     *
     * @param address The starting address to read the word
     * @return The combined 16-bit word
     */
    fun getWord(address: UShort): UShort

    /**
     * Loads a CHIP-8 program (ROM) into memory starting at address `0x200`.
     *
     * @param program Byte array representing the program
     */
    fun loadProgram(program: ByteArray)

    /**
     * Loads the standard CHIP-8 fontset (0–F) into reserved memory space.
     *
     * This is typically loaded into `0x000` to `0x050`.
     */
    fun loadFont()

    /**
     * Clears the entire memory space (sets all bytes to 0).
     */
    fun clear()
}
