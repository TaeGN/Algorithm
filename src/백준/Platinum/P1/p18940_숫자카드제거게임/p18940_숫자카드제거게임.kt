package 백준.Platinum.P1.p18940_숫자카드제거게임

fun main() {
    val setLowerThan42 = setOf(0, 4, 8, 14, 20, 24, 28, 34, 38, 42)
    val setHigherThan42 = setOf(0, 12, 16, 20, 30)
    val mod = 34
    val sb = StringBuilder()
    fun result(n: Int): String {
        if (n % 2 == 1) return "Yuto"
        if (n in setLowerThan42) return "Platina"
        if (n > 42 && (n - 42) % mod in setHigherThan42) return "Platina"
        return "Yuto"
    }
    repeat(readln().toInt()) {
        sb.appendLine(result(readln().toInt()))
    }
    println(sb)
}