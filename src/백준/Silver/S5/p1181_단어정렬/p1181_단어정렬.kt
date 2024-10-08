package 백준.Silver.S5.p1181_단어정렬

fun main() {
    println(List(readln().toInt()) { readln().trim() }
        .asSequence()
        .distinct()
        .sortedWith(compareBy({ it.length }, { it }))
        .joinToString("\n")
    )
}