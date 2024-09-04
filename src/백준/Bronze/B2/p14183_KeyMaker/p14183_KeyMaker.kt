package 백준.Bronze.B2.p14183_KeyMaker

fun main() {
    while (true) {
        val (N, M) = readln().trim().split(" ").map(String::toInt)
        if (N == 0) break
        var count = 0
        val mArr = readln().trim().split(" ").map(String::toInt)
        repeat(M) {
            var isPossible = true
            for ((idx, m) in readln().trim().split(" ").map(String::toInt).withIndex()) {
                if (mArr[idx] < m) {
                    isPossible = false
                    break
                }
            }
            if (isPossible) count++
        }
        println(count)
    }
}