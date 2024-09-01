package AtCoder.ABC.ABC369.E

const val IMPOSSIBLE = Long.MAX_VALUE shr 2
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(N + 1) { r -> LongArray(N + 1) { c -> if (r == c) 0 else IMPOSSIBLE } }
    val bridgeList = mutableListOf(Triple(0, 0, 0))
    repeat(M) {
        val (U, V, T) = readln().trim().split(" ").map(String::toInt)
        bridgeList.add(Triple(U, V, T))
        matrix[U][V] = minOf(matrix[U][V], T.toLong())
        matrix[V][U] = minOf(matrix[U][V], T.toLong())
    }

    for (k in 1..N) {
        for (i in 1..N) {
            if (i == k || matrix[i][k] == IMPOSSIBLE) continue
            for (j in 1..N) {
                matrix[i][j] = minOf(matrix[i][j], matrix[i][k] + matrix[k][j])
            }
        }
    }

    val arr = IntArray(5)
    val bArr = IntArray(5)
    fun permutation(K: Int, idx: Int = 0, flag: Int = 0): Long {
        if (idx == K) {
            var dist = 0L
            var prev = 1
            for (i in 0 until K) {
                val a = arr[i]
                val isReversed = a >= K
                val bridge = bridgeList[bArr[a % K]]
                val first = if (isReversed) bridge.second else bridge.first
                val second = if (isReversed) bridge.first else bridge.second
                dist += matrix[prev][first]
                dist += bridge.third
                prev = second
                if (dist >= IMPOSSIBLE) return IMPOSSIBLE
            }
            dist += matrix[prev][N]
            return minOf(IMPOSSIBLE, dist)
        }
        var result = IMPOSSIBLE
        for (i in 0 until 2 * K) {
            if ((flag and (1 shl (i % K))) != 0) continue
            arr[idx] = i
            result = minOf(result, permutation(K, idx + 1, flag or (1 shl (i % K))))
        }
        return result
    }

    val Q = readln().toInt()
    repeat(Q) {
        val K = readln().toInt()
        readln().trim().split(" ").map(String::toInt).forEachIndexed { index, i -> bArr[index] = i }
        println(permutation(K))
    }
}