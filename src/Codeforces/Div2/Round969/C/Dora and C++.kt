package Codeforces.Div2.Round969.C

fun main() {
    fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    val T = readln().toInt()
    val sb = StringBuilder()
    repeat(T) {
        val (N, A, B) = readln().trim().split(" ").map(String::toInt)
        val a = gcd(maxOf(A, B), minOf(A, B))
        val numArr = readln().trim().split(" ").map(String::toInt).map { it % a }.sorted()
        var result = numArr.last() - numArr.first()
        for (i in 0 until N - 1) {
            result = minOf(result, numArr[i] + a - numArr[i + 1])
        }
        sb.appendLine(result)
    }
    println(sb)
}