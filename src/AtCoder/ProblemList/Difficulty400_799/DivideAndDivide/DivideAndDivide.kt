package AtCoder.ProblemList.Difficulty400_799.DivideAndDivide

fun main() {
    val N = readln().trim().toLong()
    var map = mutableMapOf(N to 1L)
    var nMap = mutableMapOf<Long, Long>()
    var result = 0L
    while (map.isNotEmpty()) {
        for ((value, count) in map) {
            if (value / 2 > 1) nMap.compute(value / 2) { _, v -> if (v == null) count else v + count }
            if ((value + 1) / 2 > 1) nMap.compute((value + 1) / 2) { _, v -> if (v == null) count else v + count }
            result += value * count
        }
        val temp = map
        map = nMap
        nMap = temp
        nMap.clear()
    }
    println(result)
}