package 백준.Diamond.D5.p3153_타워디펜스

import kotlin.math.max

val dr = intArrayOf(0, 0, 1, -1)
val dc = intArrayOf(1, -1, 0, 0)
const val CLONE = 'n'
const val TOWER = 'T'
const val WALL = '#'
const val IMPOSSIBLE = -1

fun main() {
    val (R, C) = readln().split(" ").map(String::toInt)
    fun hash(r: Int, c: Int) = r * C + c
    fun Int.getR() = this / C
    fun Int.getC() = this % C
    val matrix = Array(R) { readln().toCharArray() }
    val towerList = mutableListOf<Int>()
    val towerIdxMap = mutableMapOf<Int, Int>()
    val cloneList = mutableListOf<Int>()
    for (r in 0 until R) {
        for (c in 0 until C) {
            val hash = hash(r, c)
            when (matrix[r][c]) {
                CLONE -> cloneList.add(hash)
                TOWER -> {
                    towerIdxMap[hash] = towerList.size
                    towerList.add(hash)
                }
            }
        }
    }

    fun Int.right() = this * 4
    fun Int.down() = this * 4 + 1
    fun Int.left() = this * 4 + 2
    fun Int.up() = this * 4 + 3
    fun Int.not() = if (this % 4 < 2) this + 2 else this - 2
    fun getTower(r: Int, c: Int, dr: Int, dc: Int): Int {
        var nr = r + dr
        var nc = c + dc
        while (nr in 0 until R && nc in 0 until C) {
            when (matrix[nr][nc]) {
                WALL -> break
                TOWER -> return hash(nr, nc)
            }
            nr += dr
            nc += dc
        }
        return IMPOSSIBLE
    }

    val outLists = List(4 * towerList.size) { mutableSetOf<Int>() }
    val towerArr = IntArray(4)
    for (clone in cloneList) {
        val cr = clone.getR()
        val cc = clone.getC()

        for (d in dr.indices) {
            towerArr[d] = getTower(cr, cc, dr[d], dc[d])
        }
        if (towerArr[0] != IMPOSSIBLE && towerArr[1] != IMPOSSIBLE) {
            towerArr[0] = IMPOSSIBLE
            towerArr[1] = IMPOSSIBLE
        }
        if (towerArr[2] != IMPOSSIBLE && towerArr[3] != IMPOSSIBLE) {
            towerArr[2] = IMPOSSIBLE
            towerArr[3] = IMPOSSIBLE
        }
        if (max(towerArr[0], towerArr[1]) != IMPOSSIBLE && max(towerArr[2], towerArr[3]) != IMPOSSIBLE) {
            when {
                towerArr[0] != IMPOSSIBLE && towerArr[2] != IMPOSSIBLE -> {
                    val idx1 = towerIdxMap[towerArr[0]]!!.left()
                    val idx2 = towerIdxMap[towerArr[2]]!!.up()
                    outLists[idx1.not()].add(idx2)
                    outLists[idx2.not()].add(idx1)
                }

                towerArr[0] != IMPOSSIBLE && towerArr[3] != IMPOSSIBLE -> {
                    val idx1 = towerIdxMap[towerArr[0]]!!.left()
                    val idx2 = towerIdxMap[towerArr[3]]!!.down()
                    outLists[idx1.not()].add(idx2)
                    outLists[idx2.not()].add(idx1)
                }

                towerArr[1] != IMPOSSIBLE && towerArr[2] != IMPOSSIBLE -> {
                    val idx1 = towerIdxMap[towerArr[1]]!!.right()
                    val idx2 = towerIdxMap[towerArr[2]]!!.up()
                    outLists[idx1.not()].add(idx2)
                    outLists[idx2.not()].add(idx1)
                }

                towerArr[1] != IMPOSSIBLE && towerArr[3] != IMPOSSIBLE -> {
                    val idx1 = towerIdxMap[towerArr[1]]!!.right()
                    val idx2 = towerIdxMap[towerArr[3]]!!.down()
                    outLists[idx1.not()].add(idx2)
                    outLists[idx2.not()].add(idx1)
                }
            }
        } else {
            when {
                towerArr[0] != IMPOSSIBLE -> towerIdxMap[towerArr[0]]!!.left().let { outLists[it.not()].add(it) }
                towerArr[1] != IMPOSSIBLE -> towerIdxMap[towerArr[1]]!!.right().let { outLists[it.not()].add(it) }
                towerArr[2] != IMPOSSIBLE -> towerIdxMap[towerArr[2]]!!.up().let { outLists[it.not()].add(it) }
                towerArr[3] != IMPOSSIBLE -> towerIdxMap[towerArr[3]]!!.down().let { outLists[it.not()].add(it) }
            }
        }
    }
    for (tower in towerList) {
        val tr = tower.getR()
        val tc = tower.getC()

        for (d in dr.indices) {
            towerArr[d] = getTower(tr, tc, dr[d], dc[d])
        }

        val tIdx = towerIdxMap[tower]!!
        if (towerArr[0] != IMPOSSIBLE) tIdx.right().let { outLists[it].add(it.not()) }
        if (towerArr[1] != IMPOSSIBLE) tIdx.left().let { outLists[it].add(it.not()) }
        if (towerArr[2] != IMPOSSIBLE) tIdx.down().let { outLists[it].add(it.not()) }
        if (towerArr[3] != IMPOSSIBLE) tIdx.up().let { outLists[it].add(it.not()) }
    }
    val stack = ArrayDeque<Int>()
    val parentArr = IntArray(4 * towerList.size) { IMPOSSIBLE }
    val sccIdArr = IntArray(4 * towerList.size) { IMPOSSIBLE }
    var sccId = 0
    var id = 0
    fun scc(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in outLists[from]) {
            if (parentArr[to] == IMPOSSIBLE) parent = minOf(parent, scc(to))
            else if (sccIdArr[to] == IMPOSSIBLE) parent = minOf(parent, parentArr[to])
        }
        if (parent == parentArr[from]) {
            sccId++
            while (true) {
                val idx = stack.removeFirst()
                sccIdArr[idx] = sccId
                if (idx == from) break
            }
        }
        return parent
    }

    for (i in sccIdArr.indices) {
        if (sccIdArr[i] == IMPOSSIBLE) scc(i)
    }
    for (i in 0 until towerList.size) {
        val isRight = sccIdArr[i.right()] < sccIdArr[i.left()]
        val isDown = sccIdArr[i.down()] < sccIdArr[i.up()]
        val r = towerList[i].getR()
        val c = towerList[i].getC()
        when {
            isRight && isDown -> matrix[r][c] = '2'
            !isRight && isDown -> matrix[r][c] = '1'
            isRight && !isDown -> matrix[r][c] = '3'
            !isRight && !isDown -> matrix[r][c] = '4'
        }
    }
    println(matrix.joinToString("\n") { it.joinToString("") })
}