package AtCoder.ProblemList.Difficulty400_799.ErrorCorrection

import java.util.StringTokenizer
import kotlin.math.abs

fun main() {
    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val T = st.nextToken()
    fun isPossible(S: String): Boolean {
        if (abs(T.length - S.length) <= 1) {
            var count = 0
            var i = 0
            var j = 0
            while (i < T.length && j < S.length) {
                if (T[i] == S[j]) {
                    count++
                    i++
                    j++
                } else {
                    if (T.length == S.length) {
                        i++
                        j++
                    } else if (T.length > S.length) i++
                    else j++
                }
            }
            return count >= maxOf(T.length, S.length) - 1
        }
        return false
    }

    var count = 0
    val sb = StringBuilder()
    repeat(N) {
        if (isPossible(readln().trim())) {
            count++
            sb.append("${it + 1} ")
        }
    }
    println(count)
    println(sb)
}