package 백준.Gold.G3.p2737_연속합

fun main() {
    val list = mutableListOf<Int>()
    var sum = 0L
    for (i in 1..Int.MAX_VALUE) {
        list.add(sum.toInt())
        sum += i
        if (sum >= Int.MAX_VALUE) break
    }
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        var count = 0
        for (i in 2 until list.size) {
            val value = list[i]
            if (value > N) break
            if ((N - value) % i == 0) count++
        }
        sb.appendLine(count)
    }
    println(sb)
}