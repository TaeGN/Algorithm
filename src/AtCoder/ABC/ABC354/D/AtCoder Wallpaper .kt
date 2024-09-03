package AtCoder.ABC.ABC354.D

const val INF = 2000000000L
fun main() {
    val (A, B, C, D) = readln().trim().split(" ").map(String::toInt)
    val arr = arrayOf(intArrayOf(2, 1, 0, 1), intArrayOf(1, 2, 1, 0))
    var result = 0L
    for (fx in 0 until 4) {
        for (fy in 0 until 2) {
            val x = (C - fx - 1 + INF) / 4 - (A - fx - 1 + INF) / 4
            val y = (D - fy - 1 + INF) / 2 - (B - fy - 1 + INF) / 2
            result += x * y * arr[fy][fx]
        }
    }
    println(result)
}