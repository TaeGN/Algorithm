package 백준.Gold.G1.p3665_최종순위

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val outLists = List(N + 1) { mutableSetOf<Int>() }
        val tArr = readln().split(" ").map(String::toInt)
        for (i in 0 until N) {
            for (j in (i + 1) until N) {
                outLists[tArr[i]].add(tArr[j])
            }
        }
        val M = readln().toInt()
        repeat(M) {
            val (A, B) = readln().split(" ").map(String::toInt)
            if (A in outLists[B]) {
                outLists[B].remove(A)
                outLists[A].add(B)
            } else if (B in outLists[A]) {
                outLists[A].remove(B)
                outLists[B].add(A)
            }
        }
        fun result(): String {
            if (outLists.groupBy { it.size }.size != N) return "IMPOSSIBLE"
            val result = IntArray(N)
            for (i in 1..N) {
                result[N - 1 - outLists[i].size] = i
            }

            for (i in 0 until N) {
                for (j in (i + 1) until N) {
                    if (result[j] !in outLists[result[i]]) return "IMPOSSIBLE"
                }
            }

            return result.joinToString(" ")
        }
        sb.appendLine(result())
    }
    println(sb)
}