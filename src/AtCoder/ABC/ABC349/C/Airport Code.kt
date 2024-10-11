package AtCoder.ABC.ABC349.C

fun main() {
    val S = readln()
    val T = readln().map(Char::lowercaseChar)
    fun result(): String {
        val endIdx = if (T.last() == 'x') 2 else 3
        var idx = 0
        for (c in S) {
            if (T[idx] == c && ++idx == endIdx) return "Yes"
        }
        return "No"
    }
    println(result())
}