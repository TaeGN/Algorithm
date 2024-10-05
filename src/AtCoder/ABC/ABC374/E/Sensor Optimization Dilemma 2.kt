package AtCoder.ABC.ABC374.E

fun main() {
    val (N, X) = readln().trim().split(" ").map(String::toInt)
    val lists = List(N) { readln().trim().split(" ").map(String::toInt) }
    fun isPossible(W: Int): Boolean {
        var sumX = 0L
        for ((A, Pa, B, Pb) in lists) {
            var curX = Long.MAX_VALUE
            if (A * Pb >= B * Pa) {
                for (i in 0..A) {
                    curX = minOf(
                        curX,
                        i.toLong() * Pb + (if ((W - B * i) > 0) ((W - B * i - 1) / A + 1) else 0).toLong() * Pa
                    )
                }
            } else {
                for (i in 0..B) {
                    curX = minOf(
                        curX,
                        i.toLong() * Pa + (if ((W - A * i) > 0) ((W - A * i - 1) / B + 1) else 0).toLong() * Pb
                    )
                }
            }
            sumX += curX
            if (sumX > X) return false
        }
        return sumX <= X
    }

    fun search(start: Int = 0, end: Int = X * 100): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(end)) end else start
        return if (isPossible(mid)) search(mid, end)
        else search(start, mid - 1)
    }
    println(search())
}