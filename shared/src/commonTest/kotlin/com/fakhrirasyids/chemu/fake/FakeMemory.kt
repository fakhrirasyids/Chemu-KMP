package com.fakhrirasyids.chemu.fake

import com.fakhrirasyids.chemu.domain.services.memory.Memory

class FakeMemory : Memory {
    private val data = UByteArray(4096)

    override fun getByte(address: UShort): UByte {
        return data[address.toInt()]
    }

    override fun setByte(address: UShort, value: UByte) {
        data[address.toInt()] = value
    }

    override fun getWord(address: UShort): UShort {
        val hi = data[address.toInt()].toInt() shl 8
        val lo = data[address.toInt() + 1].toInt()
        return (hi or lo).toUShort()
    }

    fun setWord(address: UShort, word: UShort) {
        data[address.toInt()] = (word.toInt() shr 8).toUByte()
        data[address.toInt() + 1] = (word.toInt() and 0xFF).toUByte()
    }

    override fun loadProgram(program: ByteArray) {
        program.forEachIndexed { i, byte -> data[0x200 + i] = byte.toUByte() }
    }

    override fun loadFont() {
        // No-op for test
    }

    override fun clear() {
        data.fill(0u)
    }
}
