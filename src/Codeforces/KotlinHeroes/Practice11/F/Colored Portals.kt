package Codeforces.KotlinHeroes.Practice11.F

import java.util.TreeSet
import kotlin.math.abs

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val sb = StringBuilder()
    val typeArr = arrayOf("BG", "BR", "BY", "GR", "GY", "RY")
    val typeMap = typeArr.mapIndexed { index, s -> s to index }.toMap()
    repeat(readln().toInt()) {
        val (N, Q) = readln().split(" ").map(String::toInt)
        val portal = readln().split(" ")
        val typeSets = List(typeArr.size) { TreeSet<Int>() }
        portal.forEachIndexed { index, s -> typeSets[typeMap[s]!!].add(index) }
        repeat(Q) {
            val (X, Y) = readln().split(" ").map { it.toInt() - 1 }
            var result = IMPOSSIBLE
            for ((typeIdx, type) in typeArr.withIndex()) {
                val c1 = type.first()
                val c2 = type.last()
                if (c1 != portal[X].first() && c1 != portal[X].last() && c2 != portal[X].first() && c2 != portal[X].last()) continue
                if (c1 != portal[Y].first() && c1 != portal[Y].last() && c2 != portal[Y].first() && c2 != portal[Y].last()) continue
                typeSets[typeIdx].ceiling(X)?.let { result = minOf(result, abs(X - it) + abs(Y - it)) }
                typeSets[typeIdx].floor(X)?.let { result = minOf(result, abs(X - it) + abs(Y - it)) }
            }
            sb.appendLine(if (result == IMPOSSIBLE) -1 else result)
        }
    }
    println(sb)
}