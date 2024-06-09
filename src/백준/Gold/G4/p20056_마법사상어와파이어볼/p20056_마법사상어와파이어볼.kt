package 백준.Gold.G4.p20056_마법사상어와파이어볼

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

enum class Direction(dr: Int, dc: Int) {
    N(-1, 0),
    NE(-1, 1),
    E(0, 1),
    SE(1, 1),
    S(1, 0),
    SW(1, -1),
    W(0, -1),
    NW(-1, -1);
}

data class Point(val r: Int, val c: Int)
data class FireBall(val point: Point, val mass: Int, val speed: Int, val direction: Direction)
data class FireBallUnion(val union: MutableList<FireBall> = mutableListOf()) {
    operator fun plusAssign(fireBall: FireBall) {
        union.add(fireBall)
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    for (i in 0 until m) {
        val r = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        val point = Point(r, c)
        val mass = st.nextToken().toInt()
        val speed = st.nextToken().toInt()
        val direction = Direction.entries[st.nextToken().toInt()]
        val fireBall = FireBall(point, mass, speed, direction)
    }
}
