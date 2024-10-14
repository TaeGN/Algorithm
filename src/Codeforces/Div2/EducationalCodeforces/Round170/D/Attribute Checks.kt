package Codeforces.Div2.EducationalCodeforces.Round170.D

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val R = readln().trim().split(" ").map(String::toInt)
    val count = IntArray(M + 2)
    var i = 0
    for (r in R) {
        if (r == 0) {
            for (str in M downTo 0) {
                if (count[str] < 0) {
                    count[str + 1] += count[str]
                    count[str] = 0
                }
            }
            i++
        } else if (r > 0) {
            val start = 0
            val end = minOf(i - r, M)
            if (start <= end) {
                count[start]++
                count[end + 1]--
            }
        } else {
            val start = -r
            val end = minOf(i, M)
            if (start <= end) {
                count[start]++
                count[end + 1]--
            }
        }
    }
    var result = 0
    var curResult = 0
    for (idx in 0..M) {
        curResult += count[idx]
        result = maxOf(result, curResult)
    }
    println(result)
}