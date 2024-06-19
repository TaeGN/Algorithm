package 백준.Platinum.P5.p12104_순환순열

fun main() {
    fun String.kmpFailList(): List<Int> {
        val failArr = IntArray(length)
        var j = 0
        for (i in 1 until length) {
            while (j > 0 && this[i] != this[j]) j = failArr[j - 1]
            if (this[i] == this[j]) failArr[i] = ++j
        }
        return failArr.toList()
    }

    fun String.matchCount(other: String): Int {
        val failList = other.kmpFailList()
        var matchCount = 0
        var j = 0
        for (i in indices) {
            while (j > 0 && this[i] != other[j]) j = failList[j - 1]
            if (this[i] == other[j] && ++j == failList.size) {
                matchCount++
                j = failList[j - 1]
            }
        }
        return matchCount
    }

    val br = System.`in`.bufferedReader()
    val strA = br.readLine()
    val strB = br.readLine()

    (strB + strB.substring(0, strB.length - 1)).matchCount(strA).let(::println)
}