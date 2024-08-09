package AtCoder.ABC.ABC361.D

const val EMPTY = 0

fun main() {
    fun String.hash(): Int {
        var hash = 0
        for (c in this) {
            hash = hash * 3 + if (c == 'W') 1 else 2
        }
        hash *= 9
        return hash
    }

    fun IntArray.hash(): Int {
        var hash = 0
        for (i in this) {
            hash = hash * 3 + i
        }
        return hash
    }

    fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    val N = readln().toInt()
    val S = readln()
    val T = readln()
    fun result(): Int {
        var wCount = 0
        for (c in S) {
            if (c == 'W') wCount++
        }
        for (c in T) {
            if (c == 'W') wCount--
        }
        if (wCount != 0) return -1
        val sHash = S.hash()
        val tHash = T.hash()
        if (sHash == tHash) return 0
        val queue = ArrayDeque<Int>()
        val visited = mutableSetOf<Int>()
        queue.add(sHash)
        visited.add(sHash)
        var result = 0
        val curState = IntArray(N + 2)
        val rockIdxArr = IntArray(N + 1)
        while (queue.isNotEmpty()) {
            result++
            repeat(queue.size) {
                var hash = queue.removeFirst()
                for (i in N + 1 downTo 0) {
                    curState[i] = hash % 3
                    hash /= 3
                }
                var rSize = 0
                var emptyIdx = 0
                for (i in 0 until curState.size - 1) {
                    if (curState[i] == 0 && curState[i + 1] == 0) emptyIdx = i
                    else if (curState[i] > 0 && curState[i + 1] > 0) rockIdxArr[rSize++] = i
                }
                for (rockIdx in 0 until rSize) {
                    curState.swap(rockIdxArr[rockIdx], emptyIdx)
                    curState.swap(rockIdxArr[rockIdx] + 1, emptyIdx + 1)
                    val newHash = curState.hash()
                    if (newHash == tHash) return result
                    if (visited.add(newHash)) queue.add(newHash)
                    curState.swap(rockIdxArr[rockIdx], emptyIdx)
                    curState.swap(rockIdxArr[rockIdx] + 1, emptyIdx + 1)
                }
            }
        }
        return -1
    }

    println(result())
}