package 백준.Gold.G5.p12924_멋진쌍

fun main() {
    fun Int.goodPair(maxNum: Int): Int = toString().let {
        (1 until it.length).asSequence()
            .map { i -> (it.substring(i) + it.substring(0, i)).toInt() }
            .distinct()
            .count { num -> num in (this + 1)..maxNum }
    }
    readln().split(" ").map(String::toInt).let { println((it[0]..it[1]).sumOf { num -> num.goodPair(it[1]) }) }
}