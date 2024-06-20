package 백준.Platinum.P4.p10266_시계사진들.diff

const val MAX_DEGREE = 360_000
fun main() {
    fun List<Int>.diffList(): List<Int> {
        val diffArr = IntArray(size - 1)
        for (i in 0 until (size - 1)) {
            diffArr[i] = (this[i + 1] - this[i] + MAX_DEGREE) % MAX_DEGREE
        }
        return diffArr.toList()
    }

    fun List<Int>.kmpFailList(): List<Int> {
        val failArr = IntArray(size)
        var j = 0
        for (i in 1 until size) {
            while (j > 0 && this[i] != this[j]) j = failArr[j - 1]
            if (this[i] == this[j]) failArr[i] = ++j
        }
        return failArr.toList()
    }

    fun List<Int>.isSameClock(other: List<Int>): Boolean {
        val diffA = (this + this.subList(0, size - 1)).diffList()
        val diffB = other.diffList()
        val failList = diffB.kmpFailList()
        var j = 0
        for (i in diffA.indices) {
            while (j > 0 && diffA[i] != diffB[j]) j = failList[j - 1]
            if (diffA[i] == diffB[j] && ++j == diffB.size) return true
        }
        return false
    }

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val clockA = br.readLine().split(" ").asSequence().map(String::toInt).sorted().toList()
    val clockB = br.readLine().split(" ").asSequence().map(String::toInt).sorted().toList()
    println(if (clockA.isSameClock(clockB)) "possible" else "impossible")
}