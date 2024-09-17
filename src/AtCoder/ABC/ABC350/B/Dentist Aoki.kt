package AtCoder.ABC.ABC350.B

fun main() {
    val (N, Q) = readln().split(" ").map(String::toInt)
    val arr = BooleanArray(N) { true }
    readln().split(" ").map(String::toInt).forEach { arr[it - 1] = !arr[it - 1] }
    println(arr.count { it })
}