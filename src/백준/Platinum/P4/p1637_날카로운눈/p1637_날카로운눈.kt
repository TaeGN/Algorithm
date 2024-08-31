package 백준.Platinum.P4.p1637_날카로운눈

fun main() {
    val N = readln().toInt()
    val list = mutableListOf<Triple<Int, Int, Int>>()
    repeat(N) { list.add(readln().trim().split(" ").map(String::toInt).let { Triple(it[0], it[1], it[2]) }) }
    fun isPossible(n: Int): Boolean {
        var count = 0L
        for ((A, C, B) in list) {
            if (n >= A) count += (minOf(n, C) - A) / B + 1
        }
        return count % 2 == 1L
    }

    fun search(start: Int = 1, end: Int = 2147483647): Int {
        val mid = ((start.toLong() + end) / 2).toInt()
        if (start == mid) return if (isPossible(start)) start else if (isPossible(end)) end else -1
        return if (isPossible(mid)) search(start, mid)
        else search(mid + 1, end)
    }

    fun count(n: Int): Int {
        var count = 0
        for ((A, C, B) in list) {
            if (n in A..C && (n - A) % B == 0) count++
        }
        return count
    }

    fun result(): String {
        val n = search()
        if (n == -1) return "NOTHING"
        val count = count(n)
        return "$n $count"
    }

    println(result())
}