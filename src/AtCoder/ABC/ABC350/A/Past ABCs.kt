package AtCoder.ABC.ABC350.A

fun main() {
    println(if (readln().substring(3).toInt().let { it in 1..349 && it != 316 }) "Yes" else "No")
}