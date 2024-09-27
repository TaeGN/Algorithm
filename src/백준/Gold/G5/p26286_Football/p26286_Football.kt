package 백준.Gold.G5.p26286_Football

fun main() {
    val N = readln().toInt()
    val A = readln().toInt()
    val B = readln().toInt()
    fun result(): String {
        val sb = StringBuilder()
        var count = 0
        if (N >= A + B) {
            repeat(A) { sb.appendLine("1:0") }
            repeat(B) { sb.appendLine("0:1") }
            repeat(N - (A + B)) { sb.appendLine("0:0") }
            count = N - (A + B)
        } else if (N in 2 until (A + B)) {
            if (A == 0) {
                repeat(N - 1) { sb.appendLine("0:1") }
                sb.appendLine("0:${B - (N - 1)}")
            } else if (B == 0) {
                repeat(N - 1) { sb.appendLine("1:0") }
                sb.appendLine("${A - (N - 1)}:0")
            } else if (A >= N - 1) {
                sb.appendLine("${A - (N - 2)}:0")
                repeat(N - 2) { sb.appendLine("1:0") }
                sb.appendLine("0:$B")
            } else {
                repeat(A) { sb.appendLine("1:0") }
                repeat(N - 1 - A) { sb.appendLine("0:1") }
                sb.appendLine("0:${B - (N - 1 - A)}")
            }
        } else {
            if (A == B) count = 1
            sb.appendLine("$A:$B")
        }
        return "$count\n$sb"
    }
    println(result())
}