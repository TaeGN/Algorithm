package AtCoder.ABC.ABC355.A

fun main() {
    val (A, B) = readln().trim().split(" ").map(String::toInt)
    val b = BooleanArray(4).apply { this[0] = true }
    b[A] = true
    b[B] = true
    println(if(A != B) b.indexOf(false) else -1)
}