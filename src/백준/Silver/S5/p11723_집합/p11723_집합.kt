package 백준.Silver.S5.p11723_집합

fun main() = with(System.`in`.bufferedReader()) {
    val visited = BooleanArray(21)
    val M = readLine().toInt()
    val sb = StringBuilder()
    repeat(M) {
        val (key, value) = readLine().split(" ").let { it[0] to it.getOrElse(1) { "0" }.toInt() }
        when (key) {
            "add" -> visited[value] = true
            "remove" -> visited[value] = false
            "check" -> sb.appendLine(if (visited[value]) 1 else 0)
            "toggle" -> visited[value] = !visited[value]
            "all" -> visited.fill(true)
            "empty" -> visited.fill(false)
        }
    }
    println(sb)
}