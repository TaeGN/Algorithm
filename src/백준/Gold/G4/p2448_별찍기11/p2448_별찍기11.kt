package 백준.Gold.G4.p2448_별찍기11

import kotlin.math.max

const val EMPTY = " "

fun main() {
    infix fun List<String>.sum(other: List<String>): List<String> {
        val list = MutableList(max(size, other.size)) { "" }
        for (i in list.indices) {
            if (i < this.size) list[i] += this[i]
            if (i < other.size) list[i] += other[i]
        }
        return list
    }

    fun empty(n: Int): List<String> {
        val list = MutableList(n) { "" }
        for (i in 0 until n) {
            list[i] += EMPTY.repeat(2 * (n - i) - 1)
        }
        return list
    }

    val starMap = mutableMapOf(3 to listOf("*", "* *", "*****"))
    fun star(
        n: Int
    ): List<String> {
        if (starMap.containsKey(n)) return starMap[n]!!
        val halfList = star(n / 2)
        val emptyList = empty(n / 2)
        val sbList = halfList + (halfList sum emptyList sum halfList)
        return sbList.also { starMap[n] = sbList }
    }


    val n = readln().toInt()
    val sb = StringBuilder()
    star(n).forEachIndexed { index, stringBuilder ->
        sb.append(EMPTY.repeat(n - 1 - index)).append(stringBuilder).appendLine(EMPTY.repeat(n - 1 - index))
    }
    println(sb)
}