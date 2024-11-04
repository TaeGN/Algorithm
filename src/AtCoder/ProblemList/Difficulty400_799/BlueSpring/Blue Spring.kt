package AtCoder.ProblemList.Difficulty400_799.BlueSpring

fun main() {
    val (N, D, P) = readln().trim().split(" ").map(String::toInt)
    val F = readln().trim().split(" ").map(String::toInt).sorted()
    val list = mutableListOf<Long>()
    var i = N
    while (i > 0) {
        var sum = 0L
        repeat(minOf(i, D)) { sum += F[--i] }
        list.add(sum)
    }
    println(list.sumOf { minOf(it, P.toLong()) })
}