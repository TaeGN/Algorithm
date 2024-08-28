package 백준.Gold.G4.p11657_타임머신

const val IMPOSSIBLE = Long.MAX_VALUE shr 2

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val roadList = mutableListOf<Triple<Int, Int, Int>>()
    repeat(M) {
        val (A, B, C) = readln().split(" ").map(String::toInt)
        roadList.add(Triple(A, B, C))
    }
    val distArr = LongArray(N + 1) { IMPOSSIBLE }.apply { this[1] = 0 }
    for (i in 0 until N - 1) {
        for ((A, B, C) in roadList) {
            if (distArr[A] != IMPOSSIBLE) distArr[B] = minOf(distArr[B], distArr[A] + C)
        }
    }
    fun result(): String {
        val dist2ToN = distArr.filterIndexed { i, _ -> i > 1 }
        if (dist2ToN.any { it != IMPOSSIBLE }) {
            for ((A, B, C) in roadList) {
                if (distArr[B] > distArr[A] + C) return "-1"
            }
        }
        return dist2ToN.asSequence().map { if (it == IMPOSSIBLE) -1 else it }.joinToString("\n")
    }
    println(result())
}