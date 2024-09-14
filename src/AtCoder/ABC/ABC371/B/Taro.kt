package AtCoder.ABC.ABC371.B

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val visited = BooleanArray(N + 1)
    val sb = StringBuilder()
    repeat(M) {
        val input = readln().split(" ")
        val n = input[0].toInt()
        if (input[1] == "M" && !visited[n]) {
            visited[n] = true
            sb.appendLine("Yes")
        } else sb.appendLine("No")
    }
    println(sb)
}