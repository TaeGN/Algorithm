package AtCoder.ABC.ABC354.E

fun main() {
    val N = readln().toInt()
    val list = List(N) { readln().trim().split(" ").map(String::toInt) }
    val G = IntArray(1 shl N) { 10 }.apply { this[0] = 0 }
    val selected = BooleanArray(11)
    for (flag in G.indices) {
        selected.fill(false)
        for (i in 0 until N - 1) {
            if ((flag and (1 shl i)) == 0) continue
            for (j in (i + 1) until N) {
                if ((flag and (1 shl j)) == 0) continue
                if (list[i][0] == list[j][0] || list[i][1] == list[j][1]) {
                    selected[G[(flag xor (1 shl i)) xor (1 shl j)]] = true
                }
            }
            G[flag] = selected.indexOf(false)
        }
    }
    println(if (G.last() != 0) "Takahashi" else "Aoki")
}