package 백준.Gold.G3.p2812_크게만들기

fun main() {
    val (N, K) = readln().trim().split(" ").map(String::toInt)
    val S = readln()
    val idxArr = Array(10) { ArrayDeque<Int>() }
    S.forEachIndexed { index, c -> idxArr[c.digitToInt()].add(index) }
    var k = 0
    val sb = StringBuilder()
    for (i in 0 until (N - K)) {
        if (k < K) {
            for (j in 9 downTo 0) {
                while (idxArr[j].isNotEmpty() && idxArr[j].first() < i + k) idxArr[j].removeFirst()
                if (idxArr[j].isNotEmpty() && idxArr[j].first() <= i + K) {
                    k += (idxArr[j].first() - (i + k))
                    sb.append(S[idxArr[j].removeFirst()])
                    break
                }
            }
        } else sb.append(S[i + K])
    }
    println(sb)
}