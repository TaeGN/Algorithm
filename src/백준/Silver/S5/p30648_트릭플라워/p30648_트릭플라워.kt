package 백준.Silver.S5.p30648_트릭플라워

import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())
    val a = st.nextToken().toInt()
    val b = st.nextToken().toInt()
    val R = readln().toInt()
    val set = mutableSetOf<Int>()
    var r = a
    var c = b
    var time = 0
    while (set.add(r * R + c)) {
        time++
        if (r + c + 2 < R) {
            r++
            c++
        } else {
            r /= 2
            c /= 2
        }
    }
    println(time)
}