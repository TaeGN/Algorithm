package 백준.Silver.S5.p11650_좌표정렬하기

fun main() {
    println(Array(readln().toInt()) { readln().trim().split(" ").map(String::toInt) }
        .sortedWith(compareBy({ it[0] }, { it[1] })).joinToString("\n") { "${it[0]} ${it[1]}" })
}