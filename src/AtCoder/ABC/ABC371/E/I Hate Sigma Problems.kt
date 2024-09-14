package AtCoder.ABC.ABC371.E

fun main() {
    val N = readln().toInt()
    val A = readln().split(" ").map(String::toInt)
    val map = mutableMapOf<Int, MutableList<Int>>()
    for ((i, a) in A.withIndex()) {
        map.compute(a) { _, v -> v?.also { it.add(i + 1) } ?: mutableListOf(i + 1) }
    }
    var result = 0L
    for (i in 1..N) {
        result += i.toLong() * (N + 1 - i)
    }
    for (idxList in map.values) {
        for (i in 1 until idxList.size) {
            result -= idxList[i - 1].toLong() * (N + 1 - idxList[i])
        }
    }
    println(result)
}