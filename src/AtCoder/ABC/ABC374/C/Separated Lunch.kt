package AtCoder.ABC.ABC374.C

fun main() {
    var result = Long.MAX_VALUE
    val N = readln().toInt()
    val K = readln().trim().split(" ").map(String::toInt)
    val totalK = K.sumOf { it.toLong() }
    for (flag in 0 until (1 shl N)) {
        var curResult = 0L
        for (i in 0 until N) {
            if ((flag and (1 shl i)) != 0) curResult += K[i]
        }
        result = minOf(result, maxOf(curResult, totalK - curResult))
    }
    println(result)
}