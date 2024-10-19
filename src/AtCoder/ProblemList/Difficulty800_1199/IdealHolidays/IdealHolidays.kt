package AtCoder.ProblemList.Difficulty800_1199.IdealHolidays

import java.util.TreeSet

fun main() {
    val (N, A, B) = readln().trim().split(" ").map(String::toInt)
    val D = readln().trim().split(" ").map(String::toInt)
    val set = TreeSet<Int>()
    for (d in D) {
        set.add(d % (A + B))
    }
    fun result(): String {
        if (set.last() - set.first() < A) return "Yes"
        for (a in set) {
            val b = set.higher(a) ?: break
            if (b - a > B) return "Yes"
        }
        return "No"
    }
    println(result())
}