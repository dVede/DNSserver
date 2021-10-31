package models

import DOT_CHARACTER
import java.nio.ByteBuffer

data class Question(var qname: String = "",
                    var qtype: Short = 0,
                    var qclass: Short = 0) {
    override fun toString(): String {
        return "Question(qname='$qname', qtype=$qtype, qclass=$qclass)"
    }

    companion object {
        fun getQuestionFromByteArray(question: ByteArray) : Question {
            var qName = ""
            var i = 12
            var readAmount = question[i].toInt()
            i++
            while (readAmount != 0) {
                for (j in 0 until readAmount) {
                    qName += question[i].toChar()
                    i++
                }
                readAmount = question[i].toInt()
                i++
                if (readAmount != 0) qName += DOT_CHARACTER
            }
            val qType = byteSubsequence(question, i, i + 2).short
            val qClass = byteSubsequence(question, i + 2, i + 4).short
            return Question(qName, qType, qClass)
        }

        private fun byteSubsequence(array: ByteArray, start: Int, end : Int) =
            ByteBuffer.wrap(array.copyOfRange(start, end))


    }
}

