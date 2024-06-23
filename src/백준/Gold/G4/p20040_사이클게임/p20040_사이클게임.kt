package 백준.Gold.G4.p20040_사이클게임

import java.io.StreamTokenizer

data class CycleGame(val n: Int) {
    private val HAS_NOT_GROUP = -1
    private val groupArr = IntArray(n) { HAS_NOT_GROUP }

    fun connect(idx1: Int, idx2: Int): Boolean {
        val groupId1 = getGroupId(idx1)
        val groupId2 = getGroupId(idx2)
        groupArr[groupId2] = groupId1
        return groupId1 == groupId2
    }

    private fun getGroupId(idx: Int): Int = when (groupArr[idx]) {
        HAS_NOT_GROUP, idx -> idx
        else -> getGroupId(groupArr[idx])
    }.also { groupArr[idx] = it }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val cycleGame = CycleGame(n)
    var result = 0
    repeat(m) { idx ->
        val idx1 = readInt()
        val idx2 = readInt()
        if (result == 0 && cycleGame.connect(idx1, idx2)) result = idx + 1
    }
    println(result)
}