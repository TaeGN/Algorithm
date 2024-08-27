package 백준.Gold.G2.p1039_교환

import java.util.StringTokenizer

fun main() {
    val st = StringTokenizer(readln())
    val S = st.nextToken()
    val K = st.nextToken().toInt()
    var set = mutableSetOf<String>(S)
    repeat(K) {
        val newSet = mutableSetOf<String>()
        for (s in set) {
            for (i in 0 until S.length - 1) {
                for (j in i + 1 until S.length) {
                    val ns = s.substring(0, i) + s[j] + s.substring(i + 1, j) + s[i] + s.substring(j + 1)
                    if (ns.first() != '0') newSet.add(ns)
                }
            }
        }
        set = newSet
    }
    println(if(set.isEmpty()) -1 else set.max())
}