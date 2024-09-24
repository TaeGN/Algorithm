package 백준.Platinum.P5.p16297_EatingEverythingEfficiently

const val EMPTY = -1.0
const val MIN_VALUE = Double.MIN_VALUE
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val iArr = readln().trim().split(" ").map(String::toInt)
    val outLists = List(N) { mutableListOf<Int>() }
    repeat(M) { readln().trim().split(" ").map(String::toInt).let { outLists[it[0]].add(it[1]) } }
    val dp = DoubleArray(N) { EMPTY }
    fun dfs(from: Int = 0): Double {
        if (dp[from] != EMPTY) return dp[from]
        var nextValue = 0.0
        for (to in outLists[from]) {
            nextValue = maxOf(nextValue, dfs(to))
        }
        return (maxOf(iArr[from] + nextValue / 2, nextValue)).also { dp[from] = it }
    }
    println(dfs())
}