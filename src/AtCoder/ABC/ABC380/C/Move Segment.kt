package AtCoder.ABC.ABC380.C

fun main() {
    val (N, K) = readln().trim().split(" ").map(String::toInt)
    val S = readln().trim()
    val lList = mutableListOf<Int>()
    val rList = mutableListOf<Int>()
    var prev = '0'
    for ((i, c) in S.withIndex()) {
        if (prev == '0' && c == '1') lList.add(i)
        if (prev == '1' && c == '0') rList.add(i)
        prev = c
    }
    if(S.last() == '1') rList.add(S.length)
    println(S.substring(0,rList[K - 2]) + S.substring(lList[K - 1], rList[K - 1]) + S.substring(rList[K - 2], lList[K - 1]) + S.substring(rList[K - 1]))
}