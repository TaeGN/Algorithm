package 백준.Platinum.P4.p1893_시저암호

fun main() {
    val br = System.`in`.bufferedReader()
    val sb = StringBuilder()
    val t = br.readLine().toInt()
    fun String.kmpFailList(): List<Int> {
        val kmpFailArr = IntArray(length)
        var j = 0
        for (i in 1 until length) {
            while (j > 0 && this[i] != this[j]) j = kmpFailArr[j - 1]
            if (this[i] == this[j]) kmpFailArr[i] = ++j
        }
        return kmpFailArr.toList()
    }

    fun String.matchCount(other: String): Int {
        val failList = other.kmpFailList()
        var j = 0
        var matchCount = 0
        for (i in this.indices) {
            while (j > 0 && this[i] != other[j]) j = failList[j - 1]
            if (this[i] == other[j] && ++j == other.length) {
                matchCount++
                j = failList[j - 1]
            }
        }
        return matchCount
    }

    repeat(t) {
        val alphabetSequence = br.readLine()
        val alphabetSequenceMap = alphabetSequence.asSequence().mapIndexed { index, c -> c to index }.toMap()
        val originalStr = br.readLine()
        val passwordStr = br.readLine()
        val matchShiftList = mutableListOf<Int>()
        repeat(alphabetSequence.length) { shiftCount ->
            val str =
                originalStr.map { alphabetSequence[(alphabetSequenceMap[it]!! + shiftCount) % alphabetSequence.length] }
                    .joinToString(separator = "")
            val matchCount = passwordStr.matchCount(str)
            if (matchCount == 1) matchShiftList.add(shiftCount)
        }

        when (matchShiftList.size) {
            0 -> "no solution"
            1 -> "unique: ${matchShiftList[0]}"
            else -> "ambiguous: ${matchShiftList.joinToString(" ")}"
        }.let(sb::appendLine)
    }

    println(sb)
}