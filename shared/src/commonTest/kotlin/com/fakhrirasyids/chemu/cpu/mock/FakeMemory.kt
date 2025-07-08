package com.fakhrirasyids.chemu.cpu.mock

import com.fakhrirasyids.chemu.domain.services.memory.Memory

class FakeMemory : Memory {
    private val ram = UByteArray(4096)
    override fun getByte(address: UShort): UByte {
        return 0u;
    }

    override fun setByte(address: UShort, value: UByte) {
    }

    override fun getWord(address: UShort): UShort {
        val high = ram[address.toInt()].toInt() shl 8
        val low = ram[address.toInt() + 1].toInt()
        return (high or low).toUShort()
    }
    override fun loadProgram(program: ByteArray) {
        for (i in program.indices) {
            ram[0x200 + i] = program[i].toUByte()
        }
    }
    override fun clear() = ram.fill(0u)
    override fun loadFont() {}
}