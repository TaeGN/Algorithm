package AtCoder.ABC.ABC381.A

fun main() {
    val N = readln().trim().toInt()
    val S = readln().trim()
    fun result(): String {
        val len = (N - 1) / 2
        return if ("1".repeat(len) + "/" + "2".repeat(len) == S) "Yes" else "No"
    }
    println(result())
}