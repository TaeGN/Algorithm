package 백준.Gold.G4

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet

data class Point(val r: Int, val c: Int)

class Snake(val innerDeque: Deque<Point>) : Deque<Point> by innerDeque {
    val snakeSet = HashSet<Point>()
    var isAlive = false
    override fun addFirst(e: Point?) {
        if(e != null) {
            if(!snakeSet.add(e)) isAlive = false
        }
        return innerDeque.addFirst(e)
    }

    override fun removeLast(): Point {
        val removePoint = innerDeque.removeLast()
        snakeSet.remove(removePoint)
        return removePoint
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val k = readLine().toInt()
    val map = Array(n) { BooleanArray(n) }

    for(i in 0 until k) {
        val st = StringTokenizer(readLine())
        val r = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        map[r][c] = true
    }

    val l = readLine().toInt()
}
