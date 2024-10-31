package AtCoder.ProblemList.Difficulty800_1199.Sandwiches

fun main() {
    val N = readln().trim().toInt()
    val A = readln().trim().split(" ").map(String::toInt)
    val idxLists = List(N + 1) { mutableListOf<Int>() }
    A.forEachIndexed { index, i -> idxLists[i].add(index) }
    val idxSumLists = List(N + 1) { LongArray(idxLists[it].size) }
    for (i in idxLists.indices) {
        for (j in idxLists[i].indices) {
            idxSumLists[i][j] = idxSumLists[i].getOrElse(j - 1) { 0 } + idxLists[i][j]
        }
    }
    var result = 0L
    for (i in idxLists.indices) {
        for (j in idxLists[i].indices) {
            result += (idxLists[i][j] - 1).toLong() * j - idxSumLists[i].getOrElse(j - 1) { 0 }
            if (j >= 2) result -= j.toLong() * (j - 1) / 2
        }
    }
    println(result)
}