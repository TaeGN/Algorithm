package 백준.Gold.G3.p19644_좀비떼가기관총진지에도오다니

fun main() {
    val L = readln().toInt()
    val (Ml, Mk) = readln().split(" ").map(String::toInt)
    val C = readln().toInt()
    val zArr = IntArray(L) { readln().toInt() }
    fun result(): String {
        val cArr = BooleanArray(L)
        var totalCCount = 0
        var cCount = 0
        for (i in 0 until L) {
            val Z = zArr[i]
            if (i >= Ml && cArr[i - Ml]) cCount--
            if ((minOf(i + 1, Ml).toLong() - cCount) * Mk < Z) {
                if (totalCCount >= C) return "NO"
                totalCCount++
                cCount++
                cArr[i] = true
            }
        }

        return "YES"
    }
    println(result())
}