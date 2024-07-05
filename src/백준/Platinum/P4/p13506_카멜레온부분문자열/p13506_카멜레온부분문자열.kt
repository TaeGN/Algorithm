package 백준.Platinum.P4.p13506_카멜레온부분문자열

fun main() {
    fun String.kmpFailList(): List<Int> {
        check(this.isNotEmpty())
        val kmpPattern = IntArray(length)
        var j = 0
        for (i in 1 until length) {
            while (j > 0 && this[i] != this[j]) j = kmpPattern[j - 1]
            if (this[i] == this[j]) kmpPattern[i] = ++j
        }
        return kmpPattern.toList()
    }

    fun String.chameleonSubstring(): String {
        val kmpFailList = this.kmpFailList()
        val fixLength = kmpFailList.last()
        if (fixLength == 0) return "-1"
        if (kmpFailList.count { it >= fixLength } > 1) return this.substring(0, fixLength)
        return this.substring(0, kmpFailList[fixLength - 1]).ifEmpty { "-1" }
    }

    val str = System.`in`.bufferedReader().readLine()
    str.chameleonSubstring().let(::println)
}