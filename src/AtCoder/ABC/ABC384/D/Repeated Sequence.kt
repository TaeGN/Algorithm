package AtCoder.ABC.ABC384.D

fun main() {
    val (N, S) = readln().trim().split(" ").map(String::toLong)
    val A = readln().trim().split(" ").map(String::toInt)
    val set = mutableSetOf<Long>()
    var sum = 0L
    for (a in A.reversed()) {
        set.add(sum)
        sum += a
    }
    val totalSum = A.sumOf { it.toLong() }
    fun result(): String {
        var q = S % totalSum
        for (a in A) {
            if (q in set) return "Yes"
            q = (q + totalSum - a) % totalSum
        }
        return "No"
    }
    println(result())
}