package AtCoder.ABC.ABC372.B

fun main() {
    val pow3 = IntArray(11).apply { this[0] = 1 }
    for (i in 1..10) {
        pow3[i] = pow3[i - 1] * 3
    }
    val M = readln().toInt()
    val A = IntArray(20)
    var aSize = 0
    fun dfs(idx: Int = 0, sum: Int = 0): Boolean {
        if (sum == M) {
            aSize = idx
            return true
        }
        if (idx == 20) return false
        for (i in 10 downTo 0) {
            if (sum + pow3[i] <= M) {
                A[idx] = i
                if (dfs(idx + 1, sum + pow3[i])) return true
            }
        }
        return false
    }
    dfs()
    println(aSize)
    println(A.take(aSize).joinToString(" "))
}