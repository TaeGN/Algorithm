package AtCoder.ABC.ABC355.B

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt).toSet()
    val B = readln().trim().split(" ").map(String::toInt).toSet()
    fun result(): String {
        val C = (A + B).sorted()
        for (i in 0 until C.size - 1) {
            if (C[i] in A && C[i + 1] in A) return "Yes"
        }
        return "No"
    }
    println(result())
}