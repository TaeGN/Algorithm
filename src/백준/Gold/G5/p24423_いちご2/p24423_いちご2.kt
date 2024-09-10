package 백준.Gold.G5.p24423_いちご2

data class Point(val r: Int, val c: Int)

fun main() {
    val (H, W) = readln().trim().split(" ").map(String::toInt)
    val N = readln().toInt()
    val map = mutableMapOf<Point, Int>()
    repeat(N) {
        map.compute(
            readln().trim().split(" ").map(String::toInt)
                .let { Point(it[0], it[1]) }) { _, v -> if (v == null) 1 else v + 1 }
    }
    var result = 0
    for (point in map.keys) {
        for (i in -2..0) {
            for (j in -2..0) {
                var count = 0
                for (r in (point.r + i)..(point.r + i + 2)) {
                    for (c in (point.c + j)..(point.c + j + 2)) {
                        val nPoint = Point(r, c)
                        count += map.getOrDefault(nPoint, 0)
                    }
                }
                result = maxOf(result, count)
            }
        }
    }
    println(result)
}