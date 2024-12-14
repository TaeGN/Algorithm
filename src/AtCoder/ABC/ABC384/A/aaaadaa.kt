package AtCoder.ABC.ABC384.A

fun main() {
    val (N, c1, c2) = readln().trim().split(" ")
    val S = readln().trim()
    val sb = StringBuilder()
    for (c in S) {
        sb.append(if (c.toString() == c1) c else c2)
    }
    println(sb)
}