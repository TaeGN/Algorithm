package AtCoder.ABC.ABC369.A

fun main() {
    val (A, B) = readln().trim().split(" ").map(String::toInt)
    val result = if (A == B) 1 else if ((A + B) % 2 == 0) 3 else 2
    println(result)
}