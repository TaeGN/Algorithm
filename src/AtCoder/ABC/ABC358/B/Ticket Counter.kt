package AtCoder.ABC.ABC358.B

fun main() {
    val (N, A) = readln().split(" ").map(String::toInt)
    var curT = 0
    val sb = StringBuilder()
    for (T in readln().split(" ").map(String::toInt)) {
        if (T >= curT) curT = T + A
        else curT += A
        sb.appendLine(curT)
    }
    println(sb)
}