package Codeforces.KotlinHeroes.Practice11.C

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val A = readln().split(" ").map(String::toInt)
        if (A.first() == A.last()) {
            var l = 0
            var r = N - 1
            while (l + 1 < r && A[l] == A[l + 1]) l++
            while (r - 1 > l && A[r] == A[r - 1]) r--
            sb.appendLine(maxOf(0, r - l - 1))
        } else {
            var l = 0
            var r = N - 1
            while (l + 1 < N && A[l] == A[l + 1]) l++
            while (r - 1 >= 0 && A[r] == A[r - 1]) r--
            sb.appendLine(N - maxOf(l + 1, N - r))
        }
    }
    println(sb)
}