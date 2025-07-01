package com.fakhrirasyids.chemu.data.memory

import com.fakhrirasyids.chemu.domain.memory.Memory

/*
    Author: @fakhrirasyids

    Memory implementation for CHIP-8 system.

    CHIP-8 has 4096 bytes (4KB) of memory (0x000 to 0xFFF).
    The interpreter typically reserves 0x000 to 0x1FF.
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

    companion object {
        private const val MEMORY_SIZE = 4096
    }
}
