package 백준.Platinum.P4.p16895_님게임3

fun main() {
    val N = readln().toInt()
    val pArr = readln().split(" ").map(String::toInt)
    fun result(): Int {
        val G = pArr.reduce { acc, i -> acc xor i }
        if (G == 0) return 0
        var result = 0
        for (i in 0 until N) {
            val nextG = G xor pArr[i]
            for (j in 0..pArr[i]) {
                if ((nextG xor j) == 0) result++
            }
        }
        return result
    }
    println(result())
}