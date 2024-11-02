package AtCoder.ABC.ABC378.C

fun main() {
    val N = readln().trim().toInt()
    val A = readln().trim().split(" ").map(String::toInt)
    val map = mutableMapOf<Int, Int>()
    val sb = StringBuilder()
    for (i in 0 until N) {
        sb.append("${map.getOrDefault(A[i], -1)} ")
        map[A[i]] = i + 1
    }
    println(sb)
}