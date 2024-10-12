package AtCoder.ABC.ABC375.D

fun main() {
    val S = readln()
    val idxLists = List(26) { mutableListOf<Int>() }
    for (i in S.indices) {
        idxLists[S[i] - 'A'].add(i)
    }
    val sumLists = List(26) { LongArray(idxLists[it].size) }
    for (i in idxLists.indices) {
        for (j in idxLists[i].indices) {
            sumLists[i][j] = sumLists[i].getOrElse(j - 1) { 0 } + idxLists[i][j]
        }
    }
    var result = 0L
    for (i in idxLists.indices) {
        for (j in idxLists[i].indices) {
            result += (j + 1).toLong() * idxLists[i][j] - sumLists[i][j] - j
        }
    }
    println(result)
}