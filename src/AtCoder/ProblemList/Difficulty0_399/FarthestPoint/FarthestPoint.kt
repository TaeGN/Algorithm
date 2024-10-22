package AtCoder.ProblemList.Difficulty0_399.FarthestPoint

fun main() {
    fun dist(x1: Int, y1: Int, x2: Int, y2: Int) = (x1 - x2).let { it * it } + (y1 - y2).let { it * it }
    val N = readln().trim().toInt()
    val XY = Array(N) { readln().trim().split(" ").map(String::toInt) }
    val sb = StringBuilder()
    for (i in 0 until N) {
        var maxDist = 0
        var maxDistIdx = 0
        for (j in 0 until N) {
            val dist = dist(XY[i][0], XY[i][1], XY[j][0], XY[j][1])
            if (maxDist < dist) {
                maxDist = dist
                maxDistIdx = j + 1
            }
        }
        sb.appendLine(maxDistIdx)
    }
    println(sb)
}