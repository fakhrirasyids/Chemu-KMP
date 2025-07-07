package com.fakhrirasyids.chemu.domain.services.memory

/*
    Author: @fakhrirasyids

    Memory interface for CHIP-8.
*/
interface Memory {
    fun getByte(address: UShort): UByte
    fun setByte(address: UShort, value: UByte)
    fun getWord(address: UShort): UShort
    fun loadProgram(program: ByteArray)
    fun loadFont()
    fun clear()
}