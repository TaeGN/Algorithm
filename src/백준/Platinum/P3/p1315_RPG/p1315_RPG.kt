package 백준.Platinum.P3.p1315_RPG

fun main() {
    val N = readln().toInt()
    val arr = Array(N) { readln().split(" ").map(String::toInt) }
    val maxStr = arr.maxOf { it[0] }
    val maxInt = arr.maxOf { it[1] }
    val remainedPoint = Array(maxStr + 1) { IntArray(maxInt + 1) }
    val questCount = Array(maxStr + 1) { IntArray(maxInt + 1) }
    for ((s, i, p) in arr) {
        if (s <= 1 || i <= 1) {
            remainedPoint[1][1] += p
            questCount[1][1]++
        }
    }
    for (str in 1..maxStr) {
        for (int in 1..maxInt) {
            var isPossible = false
            if (str > 1 && remainedPoint[str - 1][int] > 0) isPossible = true
            if (int > 1 && remainedPoint[str][int - 1] > 0) isPossible = true
            if (isPossible) {
                for ((s, i, p) in arr) {
                    if (s <= str || i <= int) {
                        remainedPoint[str][int] += p
                        questCount[str][int]++
                    }
                }
                remainedPoint[str][int] -= (str + int - 2)
            }
        }
    }
    println(questCount.maxOf { it.max() })
}