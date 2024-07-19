package 백준.Bronze.B2.p14697_방배정하기

import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())
    val A = st.nextToken().toInt()
    val B = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val N = st.nextToken().toInt()

    fun isPossible(): Boolean {
        for (i in 0..(N / A)) {
            for (j in 0..((N - A * i) / B)) {
                if ((N - i * A - j * B) % C == 0) return true
            }
        }
        return false
    }
    println(if (isPossible()) 1 else 0)
}