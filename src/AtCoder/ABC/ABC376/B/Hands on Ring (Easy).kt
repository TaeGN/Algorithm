package AtCoder.ABC.ABC376.B

fun main() {
    val (N, Q) = readln().trim().split(" ").map(String::toInt)
    var result = 0
    var l = 0
    var r = 1
    repeat(Q) {
        val (H, T) = readln().trim().split(" ").let { it[0] to (it[1].toInt() - 1) }
        var count = Int.MAX_VALUE shr 2
        if (H == "L") {
            if (l < r) {
                if (T in l..r) count = minOf(count, T - l)
                if (T in 0..l || T in r until N) count = minOf(count, (l + N - T) % N)
            } else {
                if (T in r..l) count = minOf(count, l - T)
                if (T in 0..r || T in l until N) count = minOf(count, (T + N - l) % N)
            }
            l = T
        } else {
            if (r < l) {
                if (T in r..l) count = minOf(count, T - r)
                if (T in 0..r || T in l until N) count = minOf(count, (r + N - T) % N)
            } else {
                if (T in l..r) count = minOf(count, r - T)
                if (T in 0..l || T in r until N) count = minOf(count, (T + N - r) % N)
            }
            r = T
        }
        result += count
    }
    println(result)
}