package AtCoder.ABC.ABC369.C

fun main() {
    val N = readln().toInt()
    val aArr = readln().trim().split(" ").map(String::toInt)
    val diffArr = (0 until N - 1).map { aArr[it + 1] - aArr[it] }
    fun result(): Long {
        if (N == 1) return 1
        var result = N.toLong()
        var count = 1
        var idx = 0
        var value = diffArr[0]
        while (++idx < diffArr.size) {
            if (diffArr[idx] == value) count++
            else {
                result += count.toLong() * (count + 1) / 2
                count = 1
                value = diffArr[idx]
            }
        }
        result += count.toLong() * (count + 1) / 2
        return result
    }

    println(result())
}