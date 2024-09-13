package 백준.Silver.S4.p1337_올바른배열

fun main() {
    val N = readln().toInt()
    val set = mutableSetOf<Int>()
    repeat(N) { set.add(readln().toInt()) }
    var minCount = Int.MAX_VALUE
    for (num in set) {
        for (i in (num - 4)..num) {
            var count = 5
            for (j in 0 until 5) {
                if ((i + j) in set) count--
            }
            minCount = minOf(minCount, count)
        }
    }
    println(minCount)
}