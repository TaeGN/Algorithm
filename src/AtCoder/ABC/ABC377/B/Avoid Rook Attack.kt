package AtCoder.ABC.ABC377.B

fun main() {
    val matrix = Array(8) { readln().trim().toCharArray() }
    val row = BooleanArray(8)
    val col = BooleanArray(8)
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            if (matrix[i][j] == '#') {
                row[i] = true
                col[j] = true
            }
        }
    }
    println(row.count { !it } * col.count { !it })
}