package AtCoder.ABC.ABC359.C

import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.max

fun main() {
    var st = StringTokenizer(readln())
    var x1 = st.nextToken().toLong()
    val y1 = st.nextToken().toLong()
    st = StringTokenizer(readln())
    var x2 = st.nextToken().toLong()
    val y2 = st.nextToken().toLong()
    if ((x1 + y1) % 2 == 1L) x1--
    if ((x2 + y2) % 2 == 1L) x2--
    val diffX = abs(x1 - x2)
    val diffY = abs(y1 - y2)
    println(diffY + max(0, diffX - diffY) / 2)
}