package 백준.Silver.S5.p1439_뒤집기

fun main() {
    val S = readln()
    var count0 = 0
    var count1 = 0
    var c = S.first()
    for (i in 1 until S.length) {
        if (c != S[i]) {
            if (c == '0') count0++
            else count1++
            c = S[i]
        }
    }
    if (c == '0') count0++
    else count1++
    println(minOf(count0, count1))
}