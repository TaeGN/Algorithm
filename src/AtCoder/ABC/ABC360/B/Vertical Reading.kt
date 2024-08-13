package AtCoder.ABC.ABC360.B

fun main() {
    val (S, T) = readln().split(" ").let { it[0] to it[1] }
    fun isPossible(): Boolean {
        for (w in 1 until S.length) {
            for (c in 1..w) {
                val sb = StringBuilder()
                for (i in (c - 1) until S.length step w) {
                    sb.append(S[i])
                }
                if (sb.toString() == T) return true
            }
        }
        return false
    }
    println(if (isPossible()) "Yes" else "No")
}