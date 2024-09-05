package 백준.Platinum.P4.p30239_트리와XOR

const val MAX_N = 200000
fun main() {
    val sb = StringBuilder()
    val aArr = IntArray(MAX_N + 1)
    val sArr = IntArray(MAX_N + 1)
    val visited = BooleanArray(MAX_N + 1)
    val minPriceArr = LongArray(MAX_N + 1)
    val roadLists = List(MAX_N + 1) { mutableListOf<Int>() }
    var N = 0

    fun init() {
        for (i in 1..N) {
            roadLists[i].clear()
            sArr[i] = 0
            minPriceArr[i] = 0
        }
    }

    fun setSArr(from: Int): Int {
        visited[from] = true
        var count = 1
        for (to in roadLists[from]) {
            if (!visited[to]) count += setSArr(to)
        }
        return count.also { sArr[from] = it }
    }

    fun setSArr() {
        visited.fill(false, 1, N + 1)
        setSArr(1)
    }

    fun minPriceByRoot1(from: Int): Long {
        visited[from] = true
        var minPriceByRoot1 = 0L
        for (to in roadLists[from]) {
            if (!visited[to]) minPriceByRoot1 += (sArr[to].toLong() * (aArr[from] xor aArr[to])) + minPriceByRoot1(to)
        }
        return minPriceByRoot1
    }

    fun minPriceByRoot1(): Long {
        visited.fill(false, 1, N + 1)
        return minPriceByRoot1(1)
    }

    fun setMinPriceArr(from: Int, minPrice: Long) {
        visited[from] = true
        minPriceArr[from] = minPrice
        for (to in roadLists[from]) {
            if (!visited[to]) {
                val xor = aArr[from] xor aArr[to]
                val fromCount = sArr[from]
                val toCount = sArr[to]
                sArr[from] = fromCount - toCount
                sArr[to] = fromCount
                setMinPriceArr(to, minPrice + ((fromCount - 2 * toCount).toLong() * xor))
                sArr[from] = fromCount
                sArr[to] = toCount
            }
        }
    }

    fun setMinPriceArr(minPriceByRoot1: Long) {
        visited.fill(false, 1, N + 1)
        setMinPriceArr(1, minPriceByRoot1)
    }

    fun setResult() {
        for (i in 1..N) {
            sb.append("${minPriceArr[i]} ")
        }
        sb.appendLine()
    }

    repeat(readln().toInt()) {
        N = readln().toInt()
        init()
        readln().trim().split(" ").map(String::toInt).forEachIndexed { idx, A -> aArr[idx + 1] = A }
        repeat(N - 1) {
            val (u, v) = readln().trim().split(" ").map(String::toInt)
            roadLists[u].add(v)
            roadLists[v].add(u)
        }
        setSArr()
        setMinPriceArr(minPriceByRoot1())
        setResult()
    }
    println(sb)
}