package AtCoder.ABC.ABC356.C

fun main() {
    val (N, M, K) = readln().split(" ").map(String::toInt)
    val recordArr = mutableListOf<IntArray>()
    val recordResult = mutableListOf<Boolean>()
    repeat(M) {
        val input = readln().split(" ")
        val C = input[0].toInt()
        val record = IntArray(C)
        for (i in 1..C) {
            record[i - 1] = input[i].toInt()
        }
        recordArr.add(record)
        recordResult.add(input.last() == "o")
    }
    val keyArr = BooleanArray(N + 1)
    fun result(idx: Int = 1): Int {
        if (idx == N + 1) {
            var isPossible = true
            for ((i, record) in recordArr.withIndex()) {
                var count = 0
                for (num in record) {
                    if (keyArr[num]) count++
                }
                if (count >= K != recordResult[i]) {
                    isPossible = false
                    break
                }
            }
            return if (isPossible) 1 else 0
        }
        keyArr[idx] = true
        var result = result(idx + 1)
        keyArr[idx] = false
        result += result(idx + 1)
        return result
    }
    println(result())
}