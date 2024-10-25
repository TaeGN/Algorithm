package AtCoder.ProblemList.Difficulty0_399.Piano

fun main() {
    val (W, B) = readln().trim().split(" ").map(String::toInt)
    val word = "wbwbwwbwbwbw"
    val wCount = word.count { it == 'w' }
    val bCount = word.length - wCount
    val countMidToEnd = Array(word.length + 1) { IntArray(2) }
    val countStartToMid = Array(word.length + 1) { IntArray(2) }
    for (i in 1..word.length) {
        countStartToMid[i][0] = countStartToMid[i - 1][0]
        countStartToMid[i][1] = countStartToMid[i - 1][1]
        if (word[i - 1] == 'w') countStartToMid[i][0]++ else countStartToMid[i][1]++
    }
    for (i in (word.length - 1) downTo 0) {
        countMidToEnd[i][0] = countMidToEnd[i + 1][0]
        countMidToEnd[i][1] = countMidToEnd[i + 1][1]
        if (word[i] == 'w') countMidToEnd[i][0]++ else countMidToEnd[i][1]++
    }
    fun result(): Boolean {
        if (W <= wCount && B <= bCount) {
            for (i in word.indices) {
                for (j in i until word.length) {
                    var curWCount = 0
                    var curBCount = 0
                    for (k in i..j) {
                        if (word[k] == 'w') curWCount++
                        else curBCount++
                    }
                    if (curWCount == W && curBCount == B) return true
                }
            }
            return false
        }
        for ((w1, b1) in countMidToEnd) {
            for ((w2, b2) in countStartToMid) {
                val w = W - w1 - w2
                val b = B - b1 - b2
                if (w >= 0 && b >= 0 && w % wCount == 0 && b % bCount == 0 && w / wCount == b / bCount) return true
            }
        }
        return false
    }
    println(if (result()) "Yes" else "No")
}