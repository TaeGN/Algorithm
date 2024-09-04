package Codeforces.Div4.Round971.D

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val zeroList = mutableListOf<Int>()
        val oneList = mutableListOf<Int>()
        val zeroVisit = BooleanArray(N + 5)
        val oneVisit = BooleanArray(N + 5)
        repeat(N) {
            val (x, y) = readln().trim().split(" ").map(String::toInt)
            if (y == 0) {
                zeroList.add(x)
                zeroVisit[x] = true
            } else {
                oneList.add(x)
                oneVisit[x] = true
            }
        }
        zeroList.sort()
        oneList.sort()
        var result = 0L
        for (x in zeroList) {
            if (oneVisit[x]) result += N - 2
            if (zeroVisit[x + 2] && oneVisit[x + 1]) result++
        }
        for (x in oneList) {
            if (oneVisit[x + 2] && zeroVisit[x + 1]) result++
        }
        sb.appendLine(result)
    }
    println(sb)
}