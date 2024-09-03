package AtCoder.ABC.ABC354.C

fun main() {
    val N = readln().toInt()
    val list = List(N) { readln().trim().split(" ").map(String::toInt) }
        .mapIndexed { index, ints -> Triple(index + 1, ints[0], ints[1]) }.sortedBy { it.second }
    var count = 1
    val result = IntArray(N)
    for (i in 1 until N) {
        while (count > 0 && list[result[count - 1]].third > list[i].third) count--
        result[count++] = i
    }
    println(count)
    println(result.take(count).map { list[it].first }.sorted().joinToString(" "))
}