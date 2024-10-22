package AtCoder.ProblemList.Difficulty0_399.PuzzleOfLamps

fun main() {
    val N = readln().toInt()
    val S = readln().trim()
    var prev = '0'
    val sb = StringBuilder()
    for (i in (N - 1) downTo 0) {
        if (S[i] != prev) {
            sb.append((if (prev == '0') "A" else "B").repeat(i + 1))
            prev = if (prev == '0') '1' else '0'
        }
    }
    println(sb.length)
    println(sb)
}