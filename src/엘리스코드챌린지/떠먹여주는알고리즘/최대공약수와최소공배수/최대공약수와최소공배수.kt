package 엘리스코드챌린지.떠먹여주는알고리즘.최대공약수와최소공배수

import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() {
    infix fun Int.gcd(o: Int): Int = if (this == 0 || o == 0) max(this, o)
    else min(this, o) gcd (max(this, o) % min(this, o))

    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val gcd = N gcd M
    val lcm = N.toLong() * M / gcd
    println("$gcd $lcm")
}