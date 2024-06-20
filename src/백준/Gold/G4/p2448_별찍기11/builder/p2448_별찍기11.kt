package 백준.Gold.G4.p2448_별찍기11.builder

const val EMPTY = " "

fun main() {
    infix fun List<StringBuilder>.sum(other: List<StringBuilder>): List<StringBuilder> {
        val sbList = List(kotlin.math.max(size, other.size)) { StringBuilder() }
        for (i in sbList.indices) {
            if (i < this.size) sbList[i].append(this[i])
            if (i < other.size) sbList[i].append(other[i])
        }
        return sbList
    }

    fun empty(n: Int): List<StringBuilder> {
        val sbList = List(n) { StringBuilder() }
        for (i in 0 until n) {
            sbList[i].append(EMPTY.repeat(2 * (n - i) - 1))
        }
        return sbList
    }

    val starMap = mutableMapOf(
        3 to listOf(
            StringBuilder("*"),
            StringBuilder("* *"),
            StringBuilder("*****")
        )
    )

    fun star(
        n: Int
    ): List<StringBuilder> {
        if (starMap.containsKey(n)) return starMap[n]!!
        val halfSbList = star(n / 2)
        val emptySbList = empty(n / 2)
        val sbList = halfSbList + (halfSbList sum emptySbList sum halfSbList)
        return sbList.also { starMap[n] = sbList }
    }

    val n = readln().toInt()
    val sb = StringBuilder()
    star(n).forEachIndexed { index, stringBuilder ->
        sb.append(EMPTY.repeat(n - 1 - index)).append(stringBuilder).appendLine(EMPTY.repeat(n - 1 - index))
    }
    println(sb)
}