package 백준.Gold.G4.p2715_상범마법팬케이크하우스

import java.util.StringTokenizer

const val MAX_M = 30

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val sb = StringBuilder()
    val cakeArr = IntArray(MAX_M + 1)
    val signArr = BooleanArray(MAX_M + 1)
    val flipArr = IntArray(3 * MAX_M - 2)
    repeat(N) {
        val st = StringTokenizer(readLine())
        val M = st.nextToken().toInt()
        repeat(M) { idx ->
            val str = st.nextToken()
            val num = str.substring(1).toInt()
            val sign = str[0] == '+'
            cakeArr[num] = idx + 1
            signArr[num] = sign
        }

        var flipCount = 0
        for (cake in M downTo 1) {
            var cakePos = cakeArr[cake]
            var cakeSign = signArr[cake]
            repeat(flipCount) { idx ->
                if (cakePos <= flipArr[idx]) {
                    cakePos = (flipArr[idx] + 1 - cakePos)
                    cakeSign = !cakeSign
                }
            }
            if (cakePos == cake && cakeSign) continue
            if (cakePos != 1) {
                flipArr[flipCount++] = cakePos
                cakeSign = !cakeSign
            }
            if (cake != 1) {
                if (cakeSign) flipArr[flipCount++] = 1
                flipArr[flipCount++] = cake
            } else {
                if (!cakeSign) flipArr[flipCount++] = 1
            }
        }

        sb.append("$flipCount ")
        for (i in 0 until flipCount) {
            sb.append("${flipArr[i]} ")
        }
        sb.appendLine()
    }

    println(sb)
}