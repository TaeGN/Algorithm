package AtCoder.ABC.ABC380.B

fun main() {
    val A = readln().trim().split("|").map(String::length)
    val sb = StringBuilder()
    for (i in 1 until (A.size - 1)) {
        sb.append("${A[i]} ")
    }
    println(sb)
}