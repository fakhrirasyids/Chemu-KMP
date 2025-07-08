package com.fakhrirasyids.chemu.domain.models

import kotlin.jvm.JvmInline

/*
    Author: @fakhrirasyids

    Class to decode 16-bit CHIP-8 instructions.

    Key concepts:
        - and       = Bitwise AND (filters specific bits)
        - shr       = Shift Right (moves each bits to the right)
        - UShort    = Unsigned 16-bit integer (perfect for CHIP-8 because it uses 16-bit)
        - UByte     = Unsigned 8-bit integer (used for register values)
*/
@JvmInline
value class OPCode(val raw: UShort) {

    // Get bits 8–11, then shift them right to get a value 0–15 (used as register Vx).
    val x: UByte get() = ((raw.toInt() and 0x0F00) shr 8).toUByte()

    // Get bits 4–7, then shift them right to get a value 0–15 (used as register Vy).
    val y: UByte get() = ((raw.toInt() and 0x00F0) shr 4).toUByte()

    // Get the lowest 4 bits (bits 0–3), commonly used for sprite height or nibble values.
    val n: UByte get() = (raw and 0x000Fu).toUByte()

    // Get the lowest 8 bits (bits 0–7), used as an 8-bit immediate constant.
    val kk: UByte get() = (raw and 0x00FFu).toUByte()

    // Get the lowest 12 bits (bits 0–11), used as an address.
    val nnn: UShort get() = (raw and 0x0FFFu).toUShort()

    // Get the top 4 bits (bits 12–15), used to identify the opcode type.
    val opcodeType: Int get() = (raw.toInt() and 0xF000) shr 12
}
