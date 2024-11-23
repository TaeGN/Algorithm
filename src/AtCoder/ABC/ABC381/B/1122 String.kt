package AtCoder.ABC.ABC381.B

fun main() {
    val S = readln().trim()
    fun result(): String {
        if ((0 until (S.length / 2)).any { S[it * 2] != S[it * 2 + 1] }) return "No"
        val count = IntArray(26)
        S.forEach { count[it - 'a']++ }
        return if (count.all { it == 0 || it == 2 }) "Yes" else "No"
    }
    println(result())
}