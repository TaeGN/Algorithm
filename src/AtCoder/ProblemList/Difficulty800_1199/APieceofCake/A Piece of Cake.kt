package AtCoder.ProblemList.Difficulty800_1199.APieceofCake
fun main() {
    val (W, H) = readln().trim().split(" ").map(String::toInt)
    val N = readln().trim().toInt()
    val posList = List(N) { readln().trim().split(" ").map(String::toInt) }
    val A = readln().trim().toInt()
    val aList = ("0 " + readln().trim()).split(" ").map(String::toInt).sorted()
    val B = readln().trim().toInt()
    val bList = ("0 " + readln().trim()).split(" ").map(String::toInt).sorted()
    val map = mutableMapOf<Pair<Int, Int>, Int>()
    for ((x, y) in posList) {
        val i = aList.binarySearch(x).let { if (it >= 0) it else -it - 1 }
        val j = bList.binarySearch(y).let { if (it >= 0) it else -it - 1 }
        map.compute(i to j) { _, v -> if (v == null) 1 else v + 1 }
    }
    val min = if ((A + 1).toLong() * (B + 1) > map.size) 0 else map.values.min()
    val max = map.values.max()
    println("$min $max")
}