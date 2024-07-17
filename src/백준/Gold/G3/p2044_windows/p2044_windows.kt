package 백준.Gold.G3.p2044_windows

import java.util.PriorityQueue
import java.util.StringTokenizer

data class Window(val title: String, val rowLength: Int, val colLength: Int) : Comparable<Window> {
    override fun compareTo(other: Window): Int = title.compareTo(other.title)
}

const val EMPTY = '.'
const val VERTEX = '+'
const val TITLE_BRACKET = '|'
const val TITLE_LINE = '-'
const val VERTICAL_LINE = '|'
const val HORIZONTAL_LINE = '-'

fun main() = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    val M = st.nextToken().toInt()
    val N = st.nextToken().toInt()
    val map = Array(M) { "" }
    repeat(M) { idx ->
        map[idx] = readLine()
    }
    val windowPQ = PriorityQueue<Window>()

    val regex = Regex("""\+-+\|([a-z]+)\|-+\+""")
    val pattern = regex.toPattern()
    repeat(M) { startR ->
        val matcher = pattern.matcher(map[startR])
        while (matcher.find()) {
            val title = matcher.group(1)
            val rowLength = matcher.group().length
            val c = matcher.start()
            var endR = startR + 1
            while (map[endR][c] == VERTICAL_LINE) endR++
            val colLength = endR - startR + 1
            windowPQ.add(Window(title, rowLength, colLength))
        }
    }

    fun List<CharArray>.addTitleLine(r: Int, c: Int, window: Window) {
        val titleLineLength = window.rowLength - window.title.length - 4
        val titleLeftLineLength = titleLineLength / 2
        val titleRightLineLength = (titleLineLength + 1) / 2
        var curC = c
        this[r][curC++] = VERTEX
        repeat(titleLeftLineLength) {
            this[r][curC++] = TITLE_LINE
        }
        this[r][curC++] = TITLE_BRACKET
        repeat(window.title.length) { idx ->
            this[r][curC++] = window.title[idx]
        }
        this[r][curC++] = TITLE_BRACKET
        repeat(titleRightLineLength) {
            this[r][curC++] = TITLE_LINE
        }
        this[r][curC++] = VERTEX
    }

    fun List<CharArray>.addVerticalLine(r: Int, c: Int, window: Window) {
        this[r][c] = VERTEX
        for (curR in (r + 1) until (r + window.colLength - 1)) {
            this[curR][c] = VERTICAL_LINE
        }
        this[r + window.colLength - 1][c] = VERTEX
    }

    fun List<CharArray>.addHorizontalLine(r: Int, c: Int, window: Window) {
        this[r][c] = VERTEX
        this[r].fill(HORIZONTAL_LINE, c + 1, c + window.rowLength - 1)
        this[r][c + window.rowLength - 1] = VERTEX
    }

    fun List<CharArray>.addEmptyArea(r: Int, c: Int, window: Window) {
        for (curR in (r + 1) until (r + window.colLength - 1)) {
            this[curR].fill(EMPTY, c + 1, c + window.rowLength - 1)
        }
    }

    fun List<CharArray>.add(r: Int, c: Int, window: Window) {
        addTitleLine(r, c, window)
        addVerticalLine(r, c, window)
        addVerticalLine(r, c + window.rowLength - 1, window)
        addHorizontalLine(r + window.colLength - 1, c, window)
        addEmptyArea(r, c, window)
    }

    val windows = List(M) { CharArray(N) { EMPTY } }
    repeat(windowPQ.size) {idx ->
        windows.add(idx, idx, windowPQ.poll())
    }

    val sb = StringBuilder()
    windows.forEach { sb.appendLine(it.joinToString("")) }
    println(sb)
}