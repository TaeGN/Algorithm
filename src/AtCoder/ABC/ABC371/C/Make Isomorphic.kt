package AtCoder.ABC.ABC371.C

fun main() {
    val N = readln().toInt()
    val gMatrix = Array(N + 1) { BooleanArray(N + 1) }
    val hMatrix = Array(N + 1) { BooleanArray(N + 1) }
    repeat(readln().toInt()) {
        readln().split(" ").map(String::toInt).let {
            val A = it[0]
            val B = it[1]
            gMatrix[A][B] = true
            gMatrix[B][A] = true
        }
    }
    repeat(readln().toInt()) {
        readln().split(" ").map(String::toInt).let {
            val A = it[0]
            val B = it[1]
            hMatrix[A][B] = true
            hMatrix[B][A] = true
        }
    }
    val aMatrix = Array(N + 1) { IntArray(N + 1) }
    repeat(N - 1) { r ->
        for ((c, A) in readln().split(" ").map(String::toInt).withIndex()) {
            aMatrix[r + 1][r + c + 2] = A
            aMatrix[r + c + 2][r + 1] = A
        }
    }
    var result = Long.MAX_VALUE
    val selected = IntArray(N + 1)
    fun run(): Long {
        var cost = 0L
        for (r in 1..N) {
            for (c in 1..N) {
                if (gMatrix[selected[r]][selected[c]] && !hMatrix[r][c]) cost += aMatrix[r][c]
                if (!gMatrix[selected[r]][selected[c]] && hMatrix[r][c]) cost += aMatrix[r][c]
            }
        }
        return cost
    }

    fun permutation(count: Int = 1, flag: Int = 0) {
        if (count == N + 1) {
            result = minOf(result, run())
            return
        }
        for (i in 1..N) {
            if ((flag and (1 shl i)) == 0) {
                selected[count] = i
                permutation(count + 1, flag or (1 shl i))
            }
        }
    }
    permutation()
    println(result / 2)
}