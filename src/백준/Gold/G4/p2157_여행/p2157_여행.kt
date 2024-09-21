package 백준.Gold.G4.p2157_여행

const val IMPOSSIBLE = Int.MIN_VALUE shr 2
fun main() {
    val (N, M, K) = readln().split(" ").map(String::toInt)
    val inLists = List(N + 1) { mutableListOf<Pair<Int, Int>>() }
    repeat(K) { readln().split(" ").map(String::toInt).let { if (it[0] < it[1]) inLists[it[1]].add(it[0] to it[2]) } }
    val dp = Array(N + 1) { IntArray(M + 1) { IMPOSSIBLE } }.apply { this[1][1] = 0 }
    for (i in 1..N) {
        for ((pi, k) in inLists[i]) {
            for (j in 2..M) {
                dp[i][j] = maxOf(dp[i][j], dp[pi][j - 1] + k)
            }
        }
    }
    println(dp[N].max())
}