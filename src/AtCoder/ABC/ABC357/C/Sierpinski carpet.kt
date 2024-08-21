package AtCoder.ABC.ABC357.C
fun main() {
    val empty = Array(7) { "." }
    val pow3 = IntArray(7).apply { this[0] = 1 }
    for (i in 1..6) {
        pow3[i] = pow3[i - 1] * 3
        empty[i] = ".".repeat(pow3[i])
    }
    val N = readln().toInt()
    fun result(n: Int): Array<String> {
        if (n == 0) return Array(1) { "#" }
        val result = Array(pow3[n]) { "" }
        val prevResult = result(n - 1)
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                for (k in prevResult.indices) {
                    result[i * prevResult.size + k] += if (i == 1 && j == 1) empty[n - 1] else prevResult[k]
                }
            }
        }
        return result
    }
    result(N).forEach { println(it) }
}