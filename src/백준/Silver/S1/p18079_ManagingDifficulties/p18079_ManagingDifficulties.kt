package 백준.Silver.S1.p18079_ManagingDifficulties

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val aArr = readln().trim().split(" ").map(String::toInt)
        val map = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until N) {
            map.compute(aArr[i]) { _, v -> v?.also { it.add(i) } ?: mutableListOf(i) }
        }
        var result = 0L
        for (i in 0 until N - 2) {
            for (j in (i + 1) until N - 1) {
                val k = 2 * aArr[j] - aArr[i]
                if (k in map) result += map[k]!!.let { list ->
                    list.size - list.binarySearch(j + 1).let { if (it >= 0) it else -it - 1 }
                }
            }
        }
        sb.appendLine(result)
    }
    println(sb)
}