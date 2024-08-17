package 백준.Platinum.P3.p13034_다각형게임

fun main() {
    fun result(N: Int): Int {
        val G = IntArray(N + 1)
        val arr = BooleanArray(N)
        for (i in 2..N) {
            arr.fill(true, 0, i)
            for (j in 0..(N - 2) / 2) {
                if (i - 2 - j >= 0) arr[G[j] xor G[i - 2 - j]] = false
            }
            G[i] = arr.indexOf(true)
        }
        return if (G[N] != 0) 1 else 2
    }
    println(result(readln().toInt()))
}