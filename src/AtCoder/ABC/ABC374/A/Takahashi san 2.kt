package AtCoder.ABC.ABC374.A

fun main() {
    val S = readln()
    fun result(): String = if (S.length >= 3 && S.substring(S.length - 3) == "san") "Yes" else "No"
    println(result())
}