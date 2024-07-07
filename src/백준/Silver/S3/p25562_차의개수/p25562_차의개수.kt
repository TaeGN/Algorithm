package 백준.Silver.S3.p25562_차의개수

fun main() {
    val N = readln().toInt()
    val sb = StringBuilder()
    sb.appendLine(N * (N - 1) / 2)
    var num = 1
    repeat(N) {
        sb.append("$num ")
        num *= 2
    }
    sb.appendLine()
    sb.appendLine(N - 1)
    for(i in 1..N) {
        sb.append("$i ")
    }

    println(sb)
}