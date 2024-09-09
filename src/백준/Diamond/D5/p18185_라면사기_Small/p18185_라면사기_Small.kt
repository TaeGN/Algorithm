package 백준.Diamond.D5.p18185_라면사기_Small

fun main() {
    val N = readln().toInt()
    val aArr = readln().trim().split(" ").map(String::toInt).toIntArray()
    var result = 0L
    val countArr = IntArray(3)
    val priceArr = intArrayOf(3, 5, 7)
    for (i in 0 until N) {
        countArr.fill(0)
        if (i < N - 2) countArr[2] = minOf(aArr[i], aArr[i + 1], aArr[i + 2])
        if (i < N - 1) countArr[1] = minOf(aArr[i], aArr[i + 1]) - countArr[2]
        while (i < N - 2 && aArr[i + 1] - countArr[1] > aArr[i + 2] && countArr[2] > 0) {
            countArr[1]++
            countArr[2]--
        }
        countArr[0] = aArr[i] - countArr[1] - countArr[2]
        for (j in 0 until 3) {
            result += countArr[j] * priceArr[j]
        }
        if (i < N - 1) aArr[i + 1] -= countArr[1] + countArr[2]
        if (i < N - 2) aArr[i + 2] -= countArr[2]
    }
    println(result)
}