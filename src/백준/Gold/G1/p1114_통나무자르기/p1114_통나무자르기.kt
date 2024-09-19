package 백준.Gold.G1.p1114_통나무자르기

fun main() {
    val (L, K, C) = readln().split(" ").map(String::toInt)
    val kArr = (readln() + " $L").split(" ").asSequence().map(String::toInt).distinct().sorted().toList()
    fun isPossible(len: Int, startIdx: Int = 0, startC: Int = 0): Boolean {
        var prev = kArr.getOrElse(startIdx - 1) { 0 }
        var c = startC
        for (idx in startIdx until kArr.size) {
            val k = kArr[idx]
            if ((k - prev) > len) {
                c++
                prev = kArr.getOrElse(idx - 1) { 0 }
                if (k - prev > len) return false
            }
        }
        return c <= C
    }

    fun search(start: Int = 0, end: Int = L): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(start)) start else end
        return if (isPossible(mid)) search(start, mid)
        else search(mid + 1, end)
    }

    fun firstIdx(len: Int): Int {
        for (i in 0 until L) {
            if (kArr[i] > len) break
            if (isPossible(len, i + 1, 1)) return kArr[i]
        }
        return -1
    }
    println(search().let { "$it ${firstIdx(it)}" })
}