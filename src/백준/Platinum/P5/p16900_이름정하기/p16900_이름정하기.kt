package 백준.Platinum.P5.p16900_이름정하기

fun main() {
    fun String.kmpFailList(): List<Int> {
        val failList = IntArray(length)
        var j = 0
        for (i in 1 until length) {
            while (j > 0 && this[i] != this[j]) j = failList[j - 1]
            if (this[i] == this[j]) failList[i] = ++j
        }
        return failList.toList()
    }

    val (str, count) = readln().split(" ").let { it[0] to it[1].toInt() }
    val length = str.length
    val matchPrefixAndSuffixLength = str.kmpFailList().last()
    println((length.toLong() - matchPrefixAndSuffixLength) * count + matchPrefixAndSuffixLength)
}