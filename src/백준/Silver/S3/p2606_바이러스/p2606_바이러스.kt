package 백준.Silver.S3.p2606_바이러스

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val parentArr = IntArray(n) { it }
    fun IntArray.find(id: Int): Int = if (parentArr[id] == id) id
    else find(parentArr[id]).also { parentArr[id] = it }

    fun IntArray.union(id1: Int, id2: Int) {
        parentArr[find(id1)] = find(id2)
    }

    repeat(m) {
        val id1 = readInt() - 1
        val id2 = readInt() - 1
        parentArr.union(id1, id2)
    }

    val groupId = parentArr.find(0)
    (parentArr.count { parentArr.find(it) == groupId } - 1).let(::println)
}