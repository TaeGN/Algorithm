package 백준.Platinum.P1.p16887_루트님게임

fun main() {
    fun Long.pow(n: Int): Long {
        var result = 1L
        for (i in 0 until n) {
            result *= this
        }
        return result
    }

    fun Int.pow(n: Int) = this.toLong().pow(n)

    val numArr = longArrayOf(4, 16, 82, 82.pow(2), 15.pow(4) + 1, (15.pow(4) + 1).pow(2), Long.MAX_VALUE)
    val G = intArrayOf(0, 1, 2, 0, 3, 1, 2)
    val N = readln().toInt()
    val aArr = readln().trim().split(" ").map(String::toLong)
    val result = aArr.fold(0) { acc, l -> acc xor G[numArr.binarySearch(l).let { if (it >= 0) it + 1 else -it - 1 }] }
    println(if (result != 0) "koosaga" else "cubelover")
}