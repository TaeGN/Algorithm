package AtCoder.ABC.ABC385.A

fun main() {
    val (A, B, C) = readln().trim().split(" ").map(String::toInt).sorted()
    fun result(): String {
        if (A == B && A == C) return "Yes"
        if (A + B == C) return "Yes"
        return "No"
    }
    println(result())
}