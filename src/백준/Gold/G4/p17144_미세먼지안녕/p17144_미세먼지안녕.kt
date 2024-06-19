package 백준.Gold.G4.p17144_미세먼지안녕

import java.io.StreamTokenizer

class House(val R: Int, val C: Int) {
    private val AIR_CLEANER = -1
    private val AIR_CLEANER_COL = 0
    private val DIFFUSION_COEFFICIENT = 5
    private val cwDr = listOf(1, 0, -1, 0)
    private val cwDc = listOf(0, 1, 0, -1)
    private val ccwDr = listOf(-1, 0, 1, 0)
    private val ccwDc = listOf(0, 1, 0, -1)

    private val dustMap = List(R) { IntArray(C) }
    private val diffusionMap = List(R) { IntArray(C) }
    private lateinit var airCleaner: Pair<Int, Int>

    fun setDust(r: Int, c: Int, dust: Int) {
        dustMap[r][c] = dust
    }

    fun setAirCleaner() {
        for (r in 0 until R) {
            if (dustMap[r][0] == AIR_CLEANER) {
                airCleaner = r to (r + 1)
                dustMap[r][0] = 0
                dustMap[r + 1][0] = 0
                return
            }
        }
    }

    fun diffusion() {
        setDiffusionMap()
        reflectDiffusionMap()
    }

    private fun setDiffusionMap() {
        for (r in 0 until R) {
            for (c in 0 until C) {
                if (dustMap[r][c] <= 0) continue
                val diffusionDust = dustMap[r][c] / DIFFUSION_COEFFICIENT
                for (d in cwDr.indices) {
                    val nextR = r + cwDr[d]
                    val nextC = c + cwDc[d]
                    if (isNotValid(nextR, nextC) || isAirCleaner(nextR, nextC)) continue
                    diffusionMap[r][c] -= diffusionDust
                    diffusionMap[nextR][nextC] += diffusionDust
                }
            }
        }
    }

    private fun reflectDiffusionMap() {
        for (r in 0 until R) {
            for (c in 0 until C) {
                dustMap[r][c] += diffusionMap[r][c]
                diffusionMap[r][c] = 0
            }
        }
    }

    fun activateAirCleaner() {
        rotate(airCleaner.first, ccwDr, ccwDc)
        rotate(airCleaner.second, cwDr, cwDc)
    }

    private fun rotate(startR: Int, dr: List<Int>, dc: List<Int>) {
        var c = AIR_CLEANER_COL
        var r = if (isValid(startR + dr[0], c)) startR + dr[0] else startR
        for (d in dr.indices) {
            while (isValid(r + dr[d], c + dc[d])) {
                dustMap[r][c] = dustMap[r + dr[d]][c + dc[d]]
                r += dr[d]
                c += dc[d]
                if (r == startR && c == C - 1) break
            }
        }
    }

    fun dustAmount(): Int = dustMap.sumOf { it.sum() }

    private fun isNotValid(r: Int, c: Int): Boolean = !isValid(r, c)
    private fun isValid(r: Int, c: Int): Boolean = r in 0 until R && c in 0 until C
    private fun isAirCleaner(r: Int, c: Int): Boolean = c == 0 && (r == airCleaner.first || r == airCleaner.second)
    override fun toString(): String {
        val sb = StringBuilder()
        val maxLength = dustMap.maxOf { it.max() }.toString().length
        repeat(R) { r ->
            repeat(C) { c ->
                val str = dustMap[r][c].toString()
                if (isAirCleaner(r, c)) sb.append(" ".repeat(maxLength - str.length)).append(-1)
                else sb.append(" ".repeat(maxLength - str.length + 1)).append(str)
            }
            sb.appendLine()
        }
        return sb.toString()
    }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val R = readInt()
    val C = readInt()
    val T = readInt()
    val house = House(R, C)
    repeat(R) { r ->
        repeat(C) { c ->
            house.setDust(r, c, readInt())
        }
    }
    house.setAirCleaner()

    repeat(T) {
        house.diffusion()
        house.activateAirCleaner()
    }

    house.dustAmount().let(::println)
}