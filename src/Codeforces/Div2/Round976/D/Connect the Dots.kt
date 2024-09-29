package Codeforces.Div2.Round976.D

import java.util.TreeMap

data class Group(val d: Int, val idx: Int) {
    override fun equals(other: Any?): Boolean {
        if (other is Group) return d == other.d && idx == other.idx
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = d
        result = 31 * result + idx
        return result
    }
}

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, M) = readln().trim().split(" ").map(String::toInt)
        val lists = List(11) { List(it) { TreeMap<Int, Int>() } }
        repeat(M) {
            val (A, D, K) = readln().trim().split(" ").map(String::toInt)
            var start = A
            var end = A + D * K
            while (true) {
                var isPossible = false
                lists[D][A % D].floorEntry(start)?.let {
                    if (it.value >= start) {
                        start = it.key
                        end = maxOf(it.value, end)
                        lists[D][A % D].remove(it.key)
                        isPossible = true
                    }
                }
                if (!isPossible) break
            }

            while (true) {
                var isPossible = false
                lists[D][A % D].higherEntry(start)?.let {
                    if (it.key <= end) {
                        end = maxOf(it.value, end)
                        lists[D][A % D].remove(it.key)
                        isPossible = true
                    }
                }
                if (!isPossible) break
            }
            lists[D][A % D][start] = end
        }
        val parents = IntArray(lists.sumOf { it.sumOf { set -> set.size } }) { it }
        fun find(id: Int): Int = if (parents[id] == id) id else find(parents[id]).also { parents[id] = it }
        fun union(id1: Int, id2: Int) {
            parents[find(id2)] = find(id1)
        }

        val groupToIdxMap = mutableMapOf<Group, Int>()
        var idx = 0
        for (d in 1..10) {
            for (treeMap in lists[d]) {
                for ((s, _) in treeMap) {
                    groupToIdxMap[Group(d, s)] = idx++
                }
            }
        }
        var result = 0
        for (a in 1..N) {
            val list = mutableListOf<Group>()
            var pGroup: Group? = null
            for (d in 1..10) {
                lists[d][a % d].floorEntry(a)?.let {
                    if (it.value >= a) {
                        val group = Group(d, it.key)
                        list.add(group)
                        if (pGroup == null) pGroup = group
                        else union(groupToIdxMap[pGroup]!!, groupToIdxMap[group]!!)
                    }
                }
            }
            if (pGroup == null) result++
        }
        for (i in parents.indices) {
            find(i)
        }
        result += parents.groupBy { it }.size
        sb.appendLine(result)
    }
    println(sb)
}