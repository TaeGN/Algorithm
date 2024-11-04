package AtCoder.ProblemList.Difficulty800_1199.Bitmask

fun main() {
    val S = readln().trim()
    val N = readln().trim().toLong()
    fun result(): Long {
        var s = S
        val n = N.toString(2)
        if (s.length > n.length) {
            for (i in 0 until (s.length - n.length)) {
                if (s[i] == '1') return -1
            }
            s = S.substring(s.length - n.length)
        }
        s = "0".repeat(n.length - s.length) + s
        val arr = IntArray(n.length)
        for (i in s.indices) {
            if (s[i] == '1') arr[i] = 1
        }
        for (i in s.indices) {
            if (s[i] == '?') {
                arr[i] = 1
                if (arr.joinToString("").toLong(2) > N) arr[i] = 0
            } else arr[i] = s[i].digitToInt()
        }
        return arr.joinToString("").toLong(2).let { if (it > N) -1 else it }
    }
    println(result())
}