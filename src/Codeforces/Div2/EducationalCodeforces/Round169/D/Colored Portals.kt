package Codeforces.Div2.EducationalCodeforces.Round169.D

import java.util.StringTokenizer
import java.util.TreeSet
import kotlin.math.abs
import kotlin.math.min

const val IMPOSSIBLE = Int.MAX_VALUE

fun main() {
    fun String.isMatch(o: String) = this[0] == o[0] || this[0] == o[1] || this[1] == o[0] || this[1] == o[1]
    val sb = StringBuilder()
    val T = readln().toInt()
    var st: StringTokenizer
    val setArr = Array(6) { TreeSet<Int>() }
    val typeArr = Array(200000) { "" }
    val typeToIdxMap = mapOf(
        "BG" to 0,
        "BR" to 1,
        "BY" to 2,
        "GR" to 3,
        "GY" to 4,
        "RY" to 5,
    )
    repeat(T) {
        setArr.forEach { it.clear() }
        st = StringTokenizer(readln())
        val N = st.nextToken().toInt()
        val Q = st.nextToken().toInt()
        st = StringTokenizer(readln())
        repeat(N) { idx ->
            val str = st.nextToken()
            typeArr[idx] = str
            setArr[typeToIdxMap[str]!!].add(idx)
        }
        repeat(Q) {
            st = StringTokenizer(readln())
            val A = st.nextToken().toInt() - 1
            val B = st.nextToken().toInt() - 1
            if (typeArr[A].isMatch(typeArr[B])) sb.appendLine(abs(A - B))
            else {
                var result = IMPOSSIBLE
                for ((type, idx) in typeToIdxMap) {
                    if (type.isMatch(typeArr[A]) && type.isMatch(typeArr[B])) {
                        result = min(result, setArr[idx].higher(A)?.let { abs(it - A) + abs(it - B) } ?: IMPOSSIBLE)
                        result = min(result, setArr[idx].lower(A)?.let { abs(it - A) + abs(it - B) } ?: IMPOSSIBLE)
                    }
                }
                sb.appendLine(if (result == IMPOSSIBLE) -1 else result)
            }
        }
    }
    println(sb)
}