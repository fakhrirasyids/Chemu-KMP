package com.fakhrirasyids.chemu.domain.models

import kotlin.jvm.JvmInline

/**
 * Author: @fakhrirasyids
 *
 * Inline class that represents and decodes a 16-bit CHIP-8 instruction (opcode).
 *
 * CHIP-8 opcodes are always 2 bytes (16 bits) and encode various operations depending
 * on their bit pattern. This class provides helper properties to extract
 * commonly used instruction fields for decoding.
 */
@JvmInline
value class OPCode(val raw: UShort) {

    /**
     * Extracts the X register index (bits 8–11).
     * Often used to refer to register Vx.
     */
    val x: UByte get() = ((raw.toInt() and 0x0F00) shr 8).toUByte()

    /**
     * Extracts the Y register index (bits 4–7).
     * Often used to refer to register Vy.
     */
    val y: UByte get() = ((raw.toInt() and 0x00F0) shr 4).toUByte()

    /**
     * Extracts the lowest 4 bits (bits 0–3).
     * Typically used for sprite height or as a nibble.
     */
    val n: UByte get() = (raw and 0x000Fu).toUByte()

    /**
     * Extracts the lowest 8 bits (bits 0–7).
     * Used as an 8-bit immediate value.
     */
    val kk: UByte get() = (raw and 0x00FFu).toUByte()

    /**
     * Extracts the lowest 12 bits (bits 0–11).
     * Used to represent addresses in memory.
     */
    val nnn: UShort get() = (raw and 0x0FFFu).toUShort()

    /**
     * Extracts the highest 4 bits (bits 12–15).
     * Used to determine the opcode category/type.
     */
    val opcodeType: Int get() = (raw.toInt() and 0xF000) shr 12
}
