package Codeforces.Div2.Round979.D

fun main() {
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val (N, Q) = readln().trim().split(" ").map(String::toInt)
        val P = readln().trim().split(" ").map(String::toInt)
        val S = readln().trim().toCharArray()
        var max = 0
        val isPossible = BooleanArray(N)
        for (i in 0 until N) {
            max = maxOf(max, P[i])
            isPossible[i] = max == (i + 1)
        }
        val set = mutableSetOf<Int>()
        for (i in 0 until (N - 1)) {
            if (S[i] == 'L' && S[i + 1] == 'R' && !isPossible[i]) set.add(i)
        }
        repeat(Q) {
            val idx = readln().trim().toInt() - 1
            if (idx in set) set.remove(idx)
            if ((idx - 1) in set) set.remove(idx - 1)
            S[idx] = if (S[idx] == 'L') 'R' else 'L'
            if (idx < (N - 1) && S[idx] == 'L' && S[idx + 1] == 'R' && !isPossible[idx]) set.add(idx)
            if (idx > 0 && S[idx - 1] == 'L' && S[idx] == 'R' && !isPossible[idx - 1]) set.add(idx - 1)
            sb.appendLine(if (set.isEmpty()) "YES" else "NO")
        }
    }
    println(sb)
}