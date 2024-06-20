package 백준.Platinum.P4.p10266_시계사진들

const val MAX_DEGREE = 360_000
fun main() {
    fun BooleanArray.kmpFailList(): List<Int> {
        val failArr = IntArray(size)
        var j = 0
        for (i in 1 until size) {
            while (j > 0 && this[i] != this[j]) j = failArr[j - 1]
            if (this[i] == this[j]) failArr[i] = ++j
        }
        return failArr.toList()
    }

    fun BooleanArray.isSameClock(other: BooleanArray): Boolean {
        val failList = other.kmpFailList()
        var j = 0
        for (i in indices) {
            while (j > 0 && this[i] != other[j]) j = failList[j - 1]
            if (this[i] == other[j] && ++j == other.size) return true
        }
        return false
    }

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val clockA = BooleanArray(MAX_DEGREE * 2)
    for (idx in br.readLine().split(" ").map(String::toInt)) {
        clockA[idx] = true
        clockA[idx + MAX_DEGREE] = true
    }
    val clockB = BooleanArray(MAX_DEGREE)
    for (idx in br.readLine().split(" ").map(String::toInt)) {
        clockB[idx] = true
    }

    println(if (clockA.isSameClock(clockB)) "possible" else "impossible")
}