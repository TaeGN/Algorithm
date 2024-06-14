package 백준.Silver.S4.p21554_마법의돌장난감

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val numList: MutableList<Int> = br.readLine().split(" ").asSequence().map(String::toInt).toMutableList()
    fun MutableList<Int>.swap(idx1: Int, idx2: Int) {
        require(idx1 < this.size && idx2 < this.size)
        val temp = this[idx1]
        this[idx1] = this[idx2]
        this[idx2] = temp
    }

    var count = 0
    val sb = StringBuilder()
    repeat(n) { idx ->
        if (numList[idx] != idx + 1) {
            count++
            val targetIdx = numList.indexOf(idx + 1)
            for (i in 0..((targetIdx - idx) / 2)) {
                numList.swap(idx + i, targetIdx - i)
            }
            sb.appendLine("${idx + 1} ${targetIdx + 1}")
        }
    }

    println("$count\n${sb}")
}