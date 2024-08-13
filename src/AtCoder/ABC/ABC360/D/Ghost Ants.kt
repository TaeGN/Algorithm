package AtCoder.ABC.ABC360.D

import java.util.StringTokenizer
fun main() {
    var st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val T = st.nextToken().toInt()
    val S = readln()
    st = StringTokenizer(readln())
    val rightNumList = mutableListOf<Int>()
    val leftNumList = mutableListOf<Int>()
    repeat(N) { idx ->
        val num = st.nextToken().toInt()
        if (S[idx] == '1') rightNumList.add(num + T)
        else leftNumList.add(num)
    }
    rightNumList.sort()

    var result = 0L
    for (num in leftNumList) {
        val minIdx = rightNumList.binarySearch(num - T).let { if (it >= 0) it else -it - 1 }
        val maxIdx = rightNumList.binarySearch(num + T).let { if (it >= 0) it else -it - 1 }
        result += maxIdx - minIdx
    }
    println(result)
}