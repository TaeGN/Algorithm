package AtCoder.ABC.ABC386.D

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val list = List(M) { readln().trim().split(" ").let { Triple(it[0].toInt(), it[1].toInt(), it[2]) } }
        .sortedWith(compareBy({ it.first }, { it.second }))
    var minY = Int.MAX_VALUE
    fun result(): String {
        for ((X, Y, C) in list) {
            if (C == "B") {
                if (minY <= Y) return "No"
            } else {
                minY = minOf(minY, Y)
            }
        }
        return "Yes"
    }
    println(result())
}