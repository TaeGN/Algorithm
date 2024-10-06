package Codeforces.Div2.Round977.C1

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, M, Q) = readln().trim().split(" ").map(String::toInt)
        val A = readln().trim().split(" ").map(String::toInt)
        val B = readln().trim().split(" ").map(String::toInt)
        fun result(): String {
            val visited = BooleanArray(N + 1)
            var i = 0
            for (j in 0 until M) {
                if (visited[B[j]]) continue
                if (A[i] != B[j]) return "TIDAK"
                if (++i == N) break
                visited[B[j]] = true
            }
            return "YA"
        }
        sb.appendLine(result())
    }
    println(sb)
}