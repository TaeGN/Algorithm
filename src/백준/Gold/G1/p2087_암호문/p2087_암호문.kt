package 백준.Gold.G1.p2087_암호문

const val EMPTY = -1
fun main() {
    val N = readln().toInt()
    val aArr = IntArray(N) { readln().toInt() }
    val leftArr = aArr.copyOfRange(0, N / 2)
    val rightArr = aArr.copyOfRange(N / 2, N)
    val K = readln().toInt()
    val map = mutableMapOf(0 to 0)
    fun leftSubSet(idx: Int = 0, count: Int = 0, flag: Int = 0, sum: Int = 0) {
        if (idx == leftArr.size) {
            if (sum <= K) map[sum] = flag
            return
        }
        leftSubSet(idx + 1, count + 1, flag or (1 shl idx), sum + leftArr[idx])
        leftSubSet(idx + 1, count, flag, sum)
    }

    fun rightSubSet(idx: Int = 0, count: Int = 0, flag: Int = 0, sum: Int = 0): Boolean {
        if (idx == rightArr.size) {
            if ((K - sum) in map) {
                val result = (map[K - sum]!!.toLong() or (flag.toLong() shl (N / 2))).toString(2).reversed()
                println(result + "0".repeat(N - result.length))
                return true
            }
            return false
        }
        if (rightSubSet(idx + 1, count + 1, flag or (1 shl idx), sum + rightArr[idx])) return true
        if (rightSubSet(idx + 1, count, flag, sum)) return true
        return false
    }
    leftSubSet()
    rightSubSet()
}