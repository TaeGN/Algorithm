package AtCoder.ProblemList.Difficulty400_799.TakahashiGetsLost

fun main() {
    val (H, W, N) = readln().trim().split(" ").map(String::toInt)
    val set = mutableSetOf(0 to 0)
    var r = 0
    var c = 0
    for (type in readln().trim()) {
        when (type) {
            'L' -> set.add(r to --c)
            'R' -> set.add(r to ++c)
            'U' -> set.add(--r to c)
            'D' -> set.add(++r to c)
        }
    }
    val matrix = Array(H) { readln().trim().toCharArray() }
    var result = 0
    for (h in 0 until H) {
        for (w in 0 until W) {
            var isPossible = true
            for ((dh, dw) in set) {
                val nh = h + dh
                val nw = w + dw
                if (nh !in 0 until H || nw !in 0 until W || matrix[nh][nw] == '#') {
                    isPossible = false
                    break
                }
            }
            if (isPossible) result++
        }
    }
    println(result)
}