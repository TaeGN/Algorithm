package 백준.Gold.G4.p9177_단어섞기

fun main() {
    val sb = StringBuilder()
    val isPossible = Array(201) {BooleanArray(201)}
    repeat(readln().toInt()) { idx ->
        val (a, b, c) = readln().split(" ")
        for(i in 0..a.length) {
            isPossible[i].fill(false, 0, b.length + 1)
        }
        isPossible[0][0] = true
        for(aIdx in 0..a.length) {
            for(bIdx in 0..b.length) {
                if(aIdx > 0 && c[aIdx + bIdx - 1] == a[aIdx - 1] && isPossible[aIdx - 1][bIdx]) isPossible[aIdx][bIdx] = true
                if(bIdx > 0 && c[aIdx + bIdx - 1] == b[bIdx - 1] && isPossible[aIdx][bIdx - 1]) isPossible[aIdx][bIdx] = true
            }
        }
        sb.appendLine("Data set ${idx + 1}: ${if (isPossible[a.length][b.length]) "yes" else "no"}")
    }
    println(sb)
}