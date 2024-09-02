package AtCoder.ABC.ABC369.F

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    fun IntArray.upperBound(value: Int): Int {
        var start = 0
        var end = size
        while (start < end) {
            val mid = (start + end) / 2
            if (value < this[mid]) end = mid
            else start = mid + 1
        }
        return start
    }
    val (H, W, N) = readln().trim().split(" ").map(String::toInt)
    val list = mutableListOf<Pair<Int, Int>>()
    repeat(N) { list.add(readln().trim().split(" ").map(String::toInt).let { it[0] to it[1] }) }
    list.sortWith(compareBy({ it.first }, { it.second }))
    val lis = IntArray(N) { IMPOSSIBLE }
    val id = IntArray(N) { IMPOSSIBLE }
    val pre = IntArray(N)
    for ((i, pair) in list.withIndex()) {
        val ci = pair.second
        val j = lis.upperBound(ci)
        lis[j] = ci
        id[j] = i
        pre[i] = id.getOrElse(j - 1) { IMPOSSIBLE }
    }
    var now = id.findLast { it != IMPOSSIBLE }!!
    val sequence = mutableListOf(H to W)
    while (now != IMPOSSIBLE) {
        sequence.add(list[now])
        now = pre[now]
    }
    sequence.add(1 to 1)
    sequence.reverse()
    val sb = StringBuilder()
    for (i in 0 until sequence.size - 1) {
        sb.append("D".repeat(sequence[i + 1].first - sequence[i].first))
        sb.append("R".repeat(sequence[i + 1].second - sequence[i].second))
    }
    println(sequence.size - 2)
    println(sb)
}