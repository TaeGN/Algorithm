package 백준.Platinum.P3.p25582_pqbd

fun main() {
    fun manacher(word: String, str: String): Int {
        val map = mutableMapOf('#' to '#')
        for (i in str.indices step 2) {
            map[str[i]] = str[i + 1]
            map[str[i + 1]] = str[i]
        }

        val nWord = word.asSequence().joinToString("#", "#", "#")
        val rArr = IntArray(nWord.length)
        var p = 0
        var r = 0
        for (i in nWord.indices) {
            if (nWord[i] !in map || map[nWord[i]]!! != nWord[i]) continue
            if (i < r) rArr[i] = minOf(rArr[2 * p - i], r - i)
            while (i + rArr[i] + 1 < nWord.length && i - rArr[i] - 1 >= 0
                && map.getOrDefault(nWord[i + rArr[i] + 1], " ") == nWord[i - rArr[i] - 1]
            ) {
                rArr[i]++
            }
            if (r < i + rArr[i]) {
                p = i
                r = i + rArr[i]
            }
        }
        return rArr.max()
    }

    val mirror = "pqbdiimmvvwwllooxx"
    val point = "pdbqunsszzllooxx"
    val word = readln()
    println(maxOf(manacher(word, mirror), manacher(word, point)).let { if (it == 0) "NOANSWER" else it })
}