package AtCoder.ProblemList.Difficulty0_399.ABAAndBAB

fun main() {
    val map = mutableMapOf('A' to 'B', 'B' to 'A')
    val N = readln().toInt()
    val S = readln().trim().asSequence().mapIndexed { index, c -> if (index % 2 == 0) map[c]!! else c }.joinToString("")
    val countList = mutableListOf<Int>()
    var prev = S[0]
    var count = 0
    for (i in 0 until N) {
        if (prev == S[i]) count++
        else {
            countList.add(count)
            prev = S[i]
            count = 1
        }
    }
    countList.add(count)
    var result = 1L
    for (i in countList.indices) {
        result = result * ((countList[i] + 1) / 2) % 1_000_000_007
    }
    println(result)
}