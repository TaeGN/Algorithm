package Codeforces.Div2.Round972.A

fun main() {
    val sb = StringBuilder()
    val arr = charArrayOf('a', 'e', 'i', 'o', 'u')
    fun result(n: Int): String {
        val p = n / 5
        val q = n % 5
        val b = StringBuilder()
        for (i in arr.indices) {
            b.append("${arr[i]}".repeat(p + (if (i < q) 1 else 0)))
        }
        return b.toString()
    }
    repeat(readln().toInt()) {
        sb.appendLine(result(readln().toInt()))
    }
    println(sb)
}