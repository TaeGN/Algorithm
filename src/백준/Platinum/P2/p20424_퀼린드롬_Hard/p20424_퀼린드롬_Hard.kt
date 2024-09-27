package 백준.Platinum.P2.p20424_퀼린드롬_Hard

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val change = "aABbCcDdeEFfGghHIiJjKkLlMmNnOoPpQqRrsStTUuVvWwXxyYzZ"
    val changeMap = mutableMapOf<Char, Char>()
    for (i in change.indices step 2) {
        changeMap[change[i]] = change[i + 1]
    }
    val mirror = "AAE3HHIIMMOOS2TTUUVVWWXXYYZ5bddbiillmmnnoopqqpr7uuvvwwxx00112S3E5Z7r88##"
    val mirrorMap = mutableMapOf<Char, Char>()
    for (i in mirror.indices step 2) {
        mirrorMap[mirror[i]] = mirror[i + 1]
    }
    val impossible = "cfgjk469"
    val impossibleSet = impossible.toSet()
    fun result(str: String): String {
        val S = str.map { if (it in changeMap) changeMap[it]!! else it }.joinToString("")
        if (S.any { it in impossibleSet }) return "-1"
        val word = S.map { if (it in changeMap) changeMap[it] else it }.joinToString("#", "#", "#")
        val rArr = IntArray(word.length)
        var p = 0
        var r = 0
        for (i in word.indices) {
            if (word[i] !in mirrorMap || mirrorMap[word[i]] != word[i]) continue
            if (i < r) rArr[i] = minOf(rArr[2 * p - i], r - i)
            while (i + (rArr[i] + 1) < word.length && i - (rArr[i] + 1) >= 0
                && mirrorMap[word[i + (rArr[i] + 1)]] == word[i - (rArr[i] + 1)]
            ) {
                rArr[i]++
            }
            if (r < i + rArr[i]) {
                p = i
                r = i + rArr[i]
            }
        }
        var result = IMPOSSIBLE
        var isLeft = false
        for (i in word.indices) {
            if (i + rArr[i] == word.length - 1 && (S.length - rArr[i]) < result) {
                result = S.length - rArr[i]
                isLeft = false
            }
            if (i - rArr[i] == 0 && (S.length - rArr[i]) < result) {
                result = S.length - rArr[i]
                isLeft = true
            }
        }
        if (result == IMPOSSIBLE) return "-1"
        val sb = StringBuilder()
        if (isLeft) {
            val center = S.substring(0, S.length - result)
            val right = S.substring(S.length - result, S.length)
            for (i in (right.length) - 1 downTo 0) {
                sb.append(mirrorMap[right[i]])
            }
            sb.append(center).append(right)
        } else {
            val center = S.substring(result)
            val left = S.substring(0, result)
            sb.append(left).append(center)
            for (i in (left.length) - 1 downTo 0) {
                sb.append(mirrorMap[left[i]])
            }
        }
        return sb.toString()
    }
    println(result(readln()))
}