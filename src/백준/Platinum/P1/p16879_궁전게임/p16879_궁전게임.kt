package 백준.Platinum.P1.p16879_궁전게임

import java.util.StringTokenizer

fun main() {
    val N = readln().toInt()
    var result = 0
    repeat(N) {
        val st = StringTokenizer(readln())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        result = result xor (((x / 3) xor (y / 3)) * 3 + (x + y) % 3)
    }
    println(if (result != 0) "koosaga" else "cubelover")
}