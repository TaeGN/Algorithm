package 백준.Gold.G1.p2923_숫자게임

fun main() {
    val N = readln().toInt()
    val A = IntArray(101)
    val B = IntArray(101)
    val sb = StringBuilder()
    var al = Int.MAX_VALUE shr 2
    var ar = 0
    var bl = Int.MAX_VALUE shr 2
    var br = 0
    repeat(N) {
        val (a, b) = readln().trim().split(" ").map(String::toInt)
        al = minOf(al, a)
        ar = maxOf(ar, a)
        bl = minOf(bl, b)
        br = maxOf(br, b)
        A[a]++
        B[b]++
        var bIdx = br
        var result = 0
        var aCount = 0
        var bCount = B[br]
        for (aIdx in al..ar) {
            if (A[aIdx] == 0) continue
            aCount += A[aIdx]
            while (bIdx >= bl && bCount <= aCount) {
                if (B[bIdx] != 0) result = maxOf(result, aIdx + bIdx)
                bCount += B[--bIdx]
            }
        }
        sb.appendLine(result)
    }
    println(sb)
}