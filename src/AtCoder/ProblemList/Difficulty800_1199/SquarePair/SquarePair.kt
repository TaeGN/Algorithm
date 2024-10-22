package AtCoder.ProblemList.Difficulty800_1199.SquarePair

const val MAX_A = 200000
fun main() {
    val squareArr = mutableListOf<Int>()
    var i = 1
    while (i * i <= MAX_A) {
        squareArr.add(i * i)
        i++
    }
    val N = readln().trim().toInt()
    val A = readln().trim().split(" ").map(String::toInt).sorted()
    val map = mutableMapOf<Int, Int>()
    squareArr.sortDescending()
    for (a in A) {
        for (square in squareArr) {
            if (a >= square && a % square == 0) {
                map.compute(a / square) { _, v -> if (v == null) 1 else v + 1 }
                break
            }
        }
    }
    val result = A.count { it == 0 }
        .let { (2 * N - 1 - it).toLong() * it / 2 } + map.values.sumOf { it.toLong() * (it - 1) / 2 }
    println(result)
}