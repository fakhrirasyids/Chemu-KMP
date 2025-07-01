package com.fakhrirasyids.chemu.domain.memory

/*
    Author: @fakhrirasyids

    Memory interface for CHIP-8.

    The CHIP-8 memory is 4096 bytes (4 KB) from 0x000 to 0xFFF.
    The first 512 bytes (0x000–0x1FF) are typically reserved for font/sprite data.
*/
interface Memory {
    fun getByte(address: UShort): UByte
    fun setByte(address: UShort, value: UByte)
    fun getWord(address: UShort): UShort
}