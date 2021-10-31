package models

import SPACE_CHARACTER

data class Flags(var qr: Boolean = false, var opcode: Short = 0, var aa: Boolean = false, var tc: Boolean = false,
                 var rd: Boolean = false, var ra: Boolean = false, var z: Short = 0, var rcode: Short = 0) {

    companion object {
        fun ushortToFlags(flags: UShort): Flags {
            val str = String.format("%" + 16 + "s", flags.toString(radix = 2)).replace(SPACE_CHARACTER, "0")
            val qr = getBoolFromBit(str[0])
            val opCode = str.substring(1, 5).toShort(radix = 2)
            val aa = getBoolFromBit(str[5])
            val tc = getBoolFromBit(str[6])
            val rd = getBoolFromBit(str[7])
            val ra = getBoolFromBit(str[8])
            val z = str.substring(9, 12).toShort(radix = 2)
            val rCode = str.substring(12, 16).toShort(radix = 2)
            println(rCode)
            println(str)
            return Flags(qr, opCode, aa, tc, rd, ra, z, rCode)
        }

        private fun getBoolFromBit(char: Char): Boolean = char == '1'
    }

    fun toUShort(): UShort {
        val flagsStr = getBitFromBool(this.qr) +
                getBitsFromShort(this.opcode) +
                getBitFromBool(this.aa) +
                getBitFromBool(this.tc) +
                getBitFromBool(this.rd) +
                getBitFromBool(this.ra) +
                "000" + //z here - always 0
                getBitsFromShort(this.rcode)
        println("-----")
        println(getBitsFromShort(this.rcode))
        println(rcode)
        println("-----")
        return flagsStr.toUShort(radix = 2)
    }

    private fun getBitsFromShort(inShort: Short): String {
        val strBytes = shortToString(inShort)
        return strBytes.substring(strBytes.length - 4, strBytes.length)
    }

    private fun shortToString(inShort: Short): String {
        return String.format("%" + 16 + "s", inShort.toString(radix = 2)).replace(SPACE_CHARACTER.toRegex(), "0")
    }

    private fun getBitFromBool(inBool: Boolean): Char = if (inBool) '1' else '0'

    override fun toString(): String {
        return "Flags(qr=$qr, opcode=$opcode, aa=$aa, tc=$tc, rd=$rd, ra=$ra, z=$z, rcode=$rcode)"
    }

    fun toBinString() {
        val str = String.format("%" + 16 + "s", this.toUShort().toString(radix = 2)).replace(SPACE_CHARACTER, "0")
        println(str)
    }
}