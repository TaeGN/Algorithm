package AtCoder.ProblemList.Difficulty400_799.LOWER

fun main() {
    val N = readln().trim().toInt()
    val S = readln().trim().toCharArray()
    val queries = List(readln().trim().toInt()) { readln().trim().split(" ") }
    var toIndex = 0
    for (i in (queries.size - 1) downTo 0) {
        if (queries[i][0] != "1") {
            toIndex = i
            break
        }
    }
    for (i in 0 until toIndex) {
        if (queries[i][0] == "1") S[queries[i][1].toInt() - 1] = queries[i][2].first()
    }
    for (i in toIndex until queries.size) {
        if (queries[i][0] == "1") S[queries[i][1].toInt() - 1] = queries[i][2].first()
        else if (queries[i][0] == "2") S.forEachIndexed { index, c -> S[index] = c.lowercaseChar() }
        else S.forEachIndexed { index, c -> S[index] = c.uppercaseChar() }
    }
    println(S.joinToString(""))
}