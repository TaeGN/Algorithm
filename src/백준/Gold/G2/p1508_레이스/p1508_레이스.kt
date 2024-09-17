package 백준.Gold.G2.p1508_레이스

fun main() {
    val (N, M, K) = readln().split(" ").map(String::toInt)
    val kList = readln().split(" ").map(String::toInt)
    fun isPossible(dist: Int): Boolean {
        var prev = kList.first()
        var count = 1
        for (i in 1 until K) {
            val cur = kList[i]
            if (cur - prev >= dist) {
                prev = cur
                count++
            }
        }
        return count >= M
    }

    fun search(start: Int = 0, end: Int = N): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(end)) end else start
        return if (isPossible(mid)) search(mid, end)
        else search(start, mid - 1)
    }

    val dist = search()
    var prev = kList.first()
    var count = 1
    val sb = StringBuilder("1")
    for (i in 1 until K) {
        val cur = kList[i]
        if (count < M && cur - prev >= dist) {
            prev = cur
            count++
            sb.append(1)
        } else sb.append(0)
    }
    println(sb)
}