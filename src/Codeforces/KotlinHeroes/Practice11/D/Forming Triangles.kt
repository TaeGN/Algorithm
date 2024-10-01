package Codeforces.KotlinHeroes.Practice11.D

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val countArr = IntArray(N + 1)
        readln().split(" ").map(String::toInt).forEach { countArr[it]++ }
        val countSumArr = IntArray(N + 1)
        for (i in 0..N) {
            countSumArr[i] = countSumArr.getOrElse(i - 1) { 0 } + countArr[i]
        }
        var result = 0L
        for (i in 0..N) {
            if (countArr[i] >= 2) result += countArr[i].toLong() * (countArr[i] - 1) / 2 * countSumArr.getOrElse(i - 1) { 0 }
            if (countArr[i] >= 3) result += countArr[i].toLong() * (countArr[i] - 1) * (countArr[i] - 2) / 6
        }
        sb.appendLine(result)
    }
    println(sb)
}