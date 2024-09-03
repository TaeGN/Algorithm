package AtCoder.ABC.ABC354.B

fun main() {
    val N = readln().toInt()
    val list = List(N) { readln().trim().split(" ") }.sortedBy { it[0] }
    val totalRate = list.sumOf { it[1].toLong() }
    println(list[(totalRate % N).toInt()][0])
}