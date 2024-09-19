package 백준.Gold.G1.p4716_풍선

fun main() {
    val sb = StringBuilder()
    while (true) {
        val (N, A, B) = readln().split(" ").map(String::toInt)
        if (N == 0) break
        val list = List(N) { readln().split(" ").map(String::toInt).toIntArray() }.sortedBy { it[1] - it[2] }
        var aCount = A
        var bCount = B
        var result = 0L
        for (idx in list.indices) {
            val (K, Da, Db) = list[idx]
            if (Da > Db || aCount == 0) break
            val count = minOf(aCount, K)
            result += Da * count
            aCount -= count
            list[idx][0] -= count
        }
        for (idx in (list.size - 1) downTo 0) {
            val (K, Da, Db) = list[idx]
            if (Da < Db || bCount == 0) break
            val count = minOf(bCount, K)
            result += Db * count
            bCount -= count
            list[idx][0] -= count
        }
        for (idx in list.indices) {
            val (K, Da, Db) = list[idx]
            if (K == 0) continue
            if (aCount > 0) result += K * Da
            else result += K * Db
        }
        sb.appendLine(result)
    }
    println(sb)
}