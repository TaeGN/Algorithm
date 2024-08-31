package 백준.Gold.G5.p7682_틱택토

fun main() {
    fun String.isValid(): Boolean {
        val xCount = count { it == 'X' }
        val oCount = count { it == 'O' }
        var xWin = false
        var oWin = false
        for (i in 0 until 3) {
            if (this[i * 3] != '.' && this[i * 3] == this[i * 3 + 1] && this[i * 3] == this[i * 3 + 2]) {
                if (this[i * 3] == 'X') xWin = true else oWin = true
            }
            if (this[i] != '.' && this[i] == this[3 + i] && this[i] == this[6 + i]) {
                if (this[i] == 'X') xWin = true else oWin = true
            }
        }
        if (this[0] != '.' && this[0] == this[4] && this[0] == this[8]) {
            if (this[0] == 'X') xWin = true else oWin = true
        }
        if (this[2] != '.' && this[2] == this[4] && this[2] == this[6]) {
            if (this[2] == 'X') xWin = true else oWin = true
        }
        return when {
            xWin && oWin -> false
            xWin && !oWin -> xCount - oCount == 1
            !xWin && oWin -> xCount - oCount == 0
            else -> xCount - oCount in 0..1 && xCount + oCount == 9
        }
    }

    val sb = StringBuilder()
    while (true) {
        val S = readln()
        if (S == "end") break
        sb.appendLine(if (S.isValid()) "valid" else "invalid")
    }
    println(sb)
}