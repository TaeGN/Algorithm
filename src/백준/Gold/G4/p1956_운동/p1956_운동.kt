package 백준.Gold.G4.p1956_운동

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val (V, E) = readln().split(" ").map(String::toInt)
    val matrix = Array(V + 1) { IntArray(V + 1) { IMPOSSIBLE } }
    repeat(E) {
        val (a, b, c) = readln().split(" ").map(String::toInt)
        matrix[a][b] = c
    }

    for (k in 1..V) {
        for (i in 1..V) {
            if (i == k || matrix[i][k] == IMPOSSIBLE) continue
            for (j in 1..V) {
                if (k == j || matrix[k][j] == IMPOSSIBLE) continue
                matrix[i][j] = minOf(matrix[i][j], matrix[i][k] + matrix[k][j])
            }
        }
    }
    var result = IMPOSSIBLE
    for (i in 1..V) {
        result = minOf(result, matrix[i][i])
    }
    println(if (result == IMPOSSIBLE) -1 else result)
}