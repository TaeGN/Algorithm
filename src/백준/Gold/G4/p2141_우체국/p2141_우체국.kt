package 백준.Gold.G4.p2141_우체국

fun main() {
    val N = readln().toInt()
    val list = List(N) { readln().split(" ").map(String::toInt) }.sortedBy { it[0] }
    val leftSumX = DoubleArray(N)
    val rightSumX = DoubleArray(N)
    var leftSumA = list.first()[1].toLong()
    var rightSumA = list.last()[1].toLong()
    for (i in 1 until N) {
        leftSumX[i] = leftSumX[i - 1] + leftSumA.toDouble() * (list[i][0] - list[i - 1][0])
        rightSumX[N - 1 - i] = rightSumX[N - i] + rightSumA.toDouble() * (list[N - i][0] - list[N - 1 - i][0])
        leftSumA += list[i][1]
        rightSumA += list[N - 1 - i][1]
    }
    var minDist = Double.MAX_VALUE
    var minPos = 0
    for (idx in list.indices) {
        val curDist = leftSumX[idx] + rightSumX[idx]
        if (minDist > curDist + 0.5) {
            minDist = curDist
            minPos = list[idx][0]
        }
    }
    println(minPos)
}