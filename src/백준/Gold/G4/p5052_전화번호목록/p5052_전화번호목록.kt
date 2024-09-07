package 백준.Gold.G4.p5052_전화번호목록

const val MOD = (1L shl 61) - 1
fun main() {
    val hashArr = LongArray(100) { (1 until MOD).random() }
    fun result(strArr: List<String>): String {
        val set = mutableSetOf<Long>()
        for (str in strArr) {
            var hash = 0L
            for ((i, c) in str.withIndex()) {
                hash = (hash + hashArr[i * 10 + c.digitToInt()]) % MOD
                if (hash in set) return "NO"
            }
            set.add(hash)
        }
        return "YES"
    }

    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val strArr = Array(N) { readln() }.sortedBy { it.length }
        sb.appendLine(result(strArr))
    }
    println(sb)
}