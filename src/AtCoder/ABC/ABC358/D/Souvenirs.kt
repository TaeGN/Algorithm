package AtCoder.ABC.ABC358.D

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val aArr = readln().split(" ").map(String::toInt).sorted()
    val bArr = readln().split(" ").map(String::toInt).sorted()
    var aIdx = 0
    var bIdx = 0
    var result = 0L
    while (aIdx < N && bIdx < M) {
        while (aIdx < N && aArr[aIdx] < bArr[bIdx]) aIdx++
        if (aIdx == N) break
        result += aArr[aIdx]
        aIdx++
        bIdx++
    }
    println(if (bIdx == M) result else -1)
}