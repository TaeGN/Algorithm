package 백준.Silver.S5.p1543_문서검색

fun main() {
    val S = readln()
    val P = readln()
    val list = mutableListOf<Int>()
    for (i in 0..(S.length - P.length)) {
        var isPossible = true
        for (j in P.indices) {
            if (S[i + j] != P[j]) {
                isPossible = false
                break
            }
        }
        if (isPossible) list.add(i)
    }
    var count = 0
    var prev = Int.MIN_VALUE shr 2
    for (idx in list) {
        if (idx - prev < P.length) continue
        count++
        prev = idx
    }
    println(count)
}