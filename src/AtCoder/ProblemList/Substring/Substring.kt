package AtCoder.ProblemList.Substring

fun main() {
    val S = readln().trim()
    val set = mutableSetOf<String>()
    for (i in S.indices) {
        for (j in (i + 1)..S.length) {
            set.add(S.substring(i, j))
        }
    }
    println(set.size)
}