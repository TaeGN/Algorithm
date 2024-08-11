package AtCoder.ABC.ABC366.B

fun main() {
    val N = readln().toInt()
    val strList = mutableListOf<String>()
    repeat(N) {
        strList.add(readln())
    }
    val sb = StringBuilder()
    val maxLen = strList.maxOf { it.length }
    for (i in 0 until maxLen) {
        val curSb = StringBuilder()
        for (j in (N - 1) downTo 0) {
            if (strList[j].length > i) curSb.append(strList[j][i])
            else curSb.append("*")
        }
        sb.appendLine(curSb.trimEnd('*'))
    }
    println(sb)
}