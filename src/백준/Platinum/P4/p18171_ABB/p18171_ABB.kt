package 백준.Platinum.P4.p18171_ABB

fun main() {
    val N = readln().toInt()
    val S = readln()
    fun result(): Int {
        val word = S.asSequence().joinToString("#", "#", "#")
        val rArr = IntArray(word.length)
        var p = 0
        var r = 0
        for (i in word.indices) {
            if (i < r) rArr[i] = minOf(rArr[2 * p - i], r - i)
            while (i + (rArr[i] + 1) < word.length && i - (rArr[i] + 1) >= 0 && word[i + (rArr[i] + 1)] == word[i - (rArr[i] + 1)]) {
                rArr[i]++
            }
            if (r < i + rArr[i]) {
                p = i
                r = i + rArr[i]
                if (r == word.length - 1) return N - rArr[i]
            }
        }
        return N - 1
    }

    println(result())
}