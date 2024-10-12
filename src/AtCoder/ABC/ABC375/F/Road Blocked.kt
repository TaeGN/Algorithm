package AtCoder.ABC.ABC375.F

const val IMPOSSIBLE = Long.MAX_VALUE shr 2
fun main() {
    val (N, M, Q) = readln().trim().split(" ").map(String::toInt)
    val loads = Array(M) { readln().trim().split(" ").map(String::toInt) }
    val queries = Array(Q) { readln().trim().split(" ").map(String::toInt) }
    val matrix = Array(N + 1) { LongArray(N + 1) { IMPOSSIBLE } }
    val isPossible = BooleanArray(M) { true }
    queries.forEach { if (it[0] == 1) isPossible[it[1] - 1] = false }
    loads.forEachIndexed { index, (A, B, C) ->
        if (isPossible[index]) {
            matrix[A][B] = minOf(matrix[A][B], C.toLong())
            matrix[B][A] = minOf(matrix[B][A], C.toLong())
        }
    }
    for (i in 1..N) {
        matrix[i][i] = 0
    }
    for (k in 1..N) {
        for (i in 1..N) {
            if (matrix[i][k] == IMPOSSIBLE || i == k) continue
            for (j in 1..N) {
                matrix[i][j] = minOf(matrix[i][j], matrix[i][k] + matrix[k][j])
            }
        }
    }
    val result = mutableListOf<Long>()
    for (query in queries.reversed()) {
        if (query[0] == 1) {
            val (A, B, C) = loads[query[1] - 1]
            for (i in 1..N) {
                for (j in 1..N) {
                    matrix[i][j] = minOf(matrix[i][j], matrix[i][A] + C + matrix[B][j], matrix[i][B] + C + matrix[A][j])
                }
            }
        } else {
            val (_, A, B) = query
            result.add(matrix[A][B].let { if (it == IMPOSSIBLE) -1 else it })
        }
    }
    println(result.reversed().joinToString("\n"))
}