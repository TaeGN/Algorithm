package Codeforces.Div3.Round974.A

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, K) = readln().split(" ").map(String::toInt)
        val A = readln().split(" ").map(String::toInt)
        var gold = 0
        var result = 0
        for (a in A) {
            if (a >= K) gold += a
            else if (a == 0 && gold > 0) {
                gold--
                result++
            }
        }
        sb.appendLine(result)
    }
    println(sb)
}