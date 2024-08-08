package AtCoder.ABC.ABC362.A

import kotlin.math.min

fun main() {
    val (R, G, B) = readln().split(" ").map(String::toInt).let { Triple(it[0], it[1], it[2]) }
    when (readln()) {
        "Red" -> min(G, B)
        "Green" -> min(R, B)
        "Blue" -> min(R, G)
        else -> throw IllegalArgumentException()
    }.let { println(it) }
}