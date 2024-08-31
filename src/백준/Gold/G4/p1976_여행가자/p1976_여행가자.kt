package 백준.Gold.G4.p1976_여행가자

fun main() {
    val N = readln().toInt()
    val M = readln().toInt()
    val matrix = Array(N) { readln().trim().split(" ").map { it == "1" }.toBooleanArray() }
    (0 until N).forEach { matrix[it][it] = true }
    for (k in 0 until N) {
        for (i in 0 until N) {
            if (i == k || !matrix[i][k]) continue
            for (j in 0 until N) {
                matrix[i][j] = matrix[i][j] or (matrix[i][k] && matrix[k][j])
            }
        }
    }
    val list = readln().trim().split(" ").map(String::toInt)
    fun result(): String {
        var prev = list[0] - 1
        for (i in 1 until list.size) {
            val next = list[i] - 1
            if (!matrix[prev][next]) return "NO"
            prev = next
        }
        return "YES"
    }
    println(result())
}