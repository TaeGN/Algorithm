package 백준.Gold.G3.p5971_MeetingPlace

const val EMPTY = -1
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val parentArr = IntArray(N + 1)
    repeat(N - 1) { idx -> parentArr[idx + 2] = readln().toInt() }
    val depthArr = IntArray(N + 1) { EMPTY }.apply { this[1] = 0 }
    fun dfs(child: Int): Int =
        if (depthArr[child] != EMPTY) depthArr[child] else (1 + dfs(parentArr[child])).also { depthArr[child] = it }
    for (i in 1..N) {
        dfs(i)
    }
    val sb = StringBuilder()
    repeat(M) {
        val (B, J) = readln().trim().split(" ").map(String::toInt)
        var b = B
        var j = J
        while (depthArr[b] != depthArr[j]) {
            if (depthArr[b] > depthArr[j]) b = parentArr[b]
            else j = parentArr[j]
        }
        while (b != j) {
            b = parentArr[b]
            j = parentArr[j]
        }
        sb.appendLine(b)
    }
    println(sb)
}