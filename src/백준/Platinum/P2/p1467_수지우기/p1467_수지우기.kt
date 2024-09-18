package 백준.Platinum.P2.p1467_수지우기

fun main() {
    fun result(S: String, P: String): String {
        val remainedCountArr = IntArray(10)
        val removeCountArr = IntArray(10)
        S.forEach { remainedCountArr[it - '0']++ }
        P.forEach { removeCountArr[it - '0']++; remainedCountArr[it - '0']-- }
        val sb = StringBuilder()
        var s = S
        val countArr = IntArray(10)
        repeat(S.length - P.length) {
            for (i in 9 downTo 0) {
                if (remainedCountArr[i] <= 0) continue
                val idx = s.indexOf('0' + i)
                if (idx == -1) continue
                countArr.fill(0)
                for (j in 0 until idx) {
                    countArr[s[j] - '0']++
                }
                if ((0..9).all { removeCountArr[it] >= countArr[it] }) {
                    for (j in 0 until 9) {
                        removeCountArr[j] -= countArr[j]
                    }
                    sb.append(i)
                    s = s.substring(idx + 1)
                    remainedCountArr[i]--
                    break
                }
            }
        }
        return sb.toString()
    }
    println(result(readln(), readln()))
}