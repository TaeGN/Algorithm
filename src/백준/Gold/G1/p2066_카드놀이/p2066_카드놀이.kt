package 백준.Gold.G1.p2066_카드놀이

const val EMPTY = -1

class IntArrayHash(val arr: IntArray) {
    override fun equals(other: Any?): Boolean {
        if (other is IntArrayHash) return arr.contentEquals(other.arr)
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return arr.contentHashCode()
    }
}

fun main() {
    val matrix = Array(9) { readln().trim().split(" ") }
    val map = mutableMapOf(IntArrayHash(IntArray(9)) to 1.0)
    fun dp(idxArr: IntArray = IntArray(9) { 4 }): Double {
        val hash = IntArrayHash(idxArr)
        if (hash in map) return map[hash]!!
        var count = 0
        for (i in idxArr.indices) {
            if (idxArr[i] == 0) continue
            for (j in (i + 1) until idxArr.size) {
                if (idxArr[j] == 0 || matrix[i][idxArr[i] - 1][0] != matrix[j][idxArr[j] - 1][0]) continue
                count++
            }
        }
        var result = 0.0
        if (count > 0) {
            for (i in idxArr.indices) {
                if (idxArr[i] == 0) continue
                for (j in (i + 1) until idxArr.size) {
                    if (idxArr[j] == 0 || matrix[i][idxArr[i] - 1][0] != matrix[j][idxArr[j] - 1][0]) continue
                    val nIdxArr = idxArr.copyOf()
                    nIdxArr[i]--
                    nIdxArr[j]--
                    result += dp(nIdxArr) / count
                }
            }
        }

        return result.also { map[hash] = it }
    }
    println(dp())
}