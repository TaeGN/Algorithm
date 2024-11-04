package AtCoder.ProblemList.Difficulty400_799.MinimumWidth

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val L = readln().trim().split(" ").map(String::toInt)
    fun isPossible(W: Long): Boolean {
        var m = 1
        var w = -1L
        for (l in L) {
            if (w + (l + 1) <= W) w += l + 1
            else {
                m++
                w = l.toLong()
            }
        }
        return m <= M
    }

    fun search(start: Long = L.max().toLong(), end: Long = Long.MAX_VALUE shr 2): Long {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(start)) start else end
        return if (isPossible(mid)) search(start, mid)
        else search(mid + 1, end)
    }

    println(search())
}