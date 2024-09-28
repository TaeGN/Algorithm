package 백준.Gold.G2.p10800_컬러볼

fun main() {
    val N = readln().toInt()
    val balls = Array(N) { idx ->
        readln().trim().split(" ").map(String::toInt).let { intArrayOf(it[0], it[1], idx) }
    }.sortedBy { it[1] }
    val colorBalls = Array(N + 1) { mutableListOf<Int>() }
    balls.forEach { colorBalls[it[0]].add(it[1]) }
    val ballSums = IntArray(2001)
    for (i in 0 until N) {
        ballSums[balls[i][1]] += balls[i][1]
    }
    for (i in ballSums.indices) {
        ballSums[i] += ballSums.getOrElse(i - 1) { 0 }
    }
    val colorBallSums = Array(N + 1) { IntArray(colorBalls[it].size) }
    for (i in 1..N) {
        for (j in colorBalls[i].indices) {
            colorBallSums[i][j] = colorBallSums[i].getOrElse(j - 1) { 0 } + colorBalls[i][j]
        }
    }
    fun List<Int>.upperbound(target: Int, start: Int = 0, end: Int = size): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (this[start] <= target) end else start
        return if (this[mid] <= target) upperbound(target, mid + 1, end)
        else upperbound(target, start, mid)
    }

    val result = IntArray(N)
    for (i in 0 until N) {
        val (color, size, id) = balls[i]
        result[id] += ballSums[size - 1]
        val idx = colorBalls[color].upperbound(size - 1)
        result[id] -= colorBallSums[color].getOrElse(idx - 1) { 0 }
    }
    println(result.joinToString("\n"))
}