package AtCoder.ProblemList.Difficulty1100.ChmaxRush

const val MOD = 998244353

enum class Direction { L, R, LR }

fun main() {
    val (N, Q) = readln().trim().split(" ").map(String::toInt)
    val D = Array(Q) { Direction.LR }
    val PV = Array(Q) { readln().trim().split(" ").map(String::toInt) }
    fun result(): Int {
        for (i in 0 until Q) {
            for (j in (i + 1) until Q) {
                if (PV[i][1] <= PV[j][1]) continue
                if (PV[i][0] == PV[j][0]) return 0
                if (PV[i][0] < PV[j][0]) {
                    if (D[i] == Direction.R || D[j] == Direction.L) return 0
                    D[i] = Direction.L
                    D[j] = Direction.R
                } else {
                    if (D[i] == Direction.L || D[j] == Direction.R) return 0
                    D[i] = Direction.R
                    D[j] = Direction.L
                }
            }
        }
        return D.fold(1) { acc, d -> if (d == Direction.LR) (acc.toLong() * 2 % MOD).toInt() else acc }
    }
    println(result())
}