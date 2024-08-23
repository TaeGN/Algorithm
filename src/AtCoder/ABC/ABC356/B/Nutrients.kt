package AtCoder.ABC.ABC356.B

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val aArr = readln().split(" ").map(String::toInt).toIntArray()
    repeat(N) {
        val xArr = readln().split(" ").map(String::toInt)
        for (i in 0 until M) {
            aArr[i] -= xArr[i]
        }
    }
    println(if (aArr.all { it <= 0 }) "Yes" else "No")
}