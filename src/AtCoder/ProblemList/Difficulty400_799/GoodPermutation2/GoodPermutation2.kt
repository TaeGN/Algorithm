package AtCoder.ProblemList.Difficulty400_799.GoodPermutation2

import java.util.TreeSet

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt).sorted()
    fun result(): String {
        if (A.first() == 1) return "-1"
        val sb = StringBuilder()
        var aIdx = 0
        var max = 0
        val numbers = TreeSet((1..N).toSet())
        for (i in 1..N) {
            var num = numbers.first()
            while (aIdx < M && maxOf(max, num) == i && maxOf(max, num) == A[aIdx]) {
                num = numbers.higher(num) ?: return "-1"
                aIdx++
            }
            max = maxOf(max, num)
            sb.append("$num ")
            numbers.remove(num)
        }
        return sb.toString()
    }
    println(result())
}