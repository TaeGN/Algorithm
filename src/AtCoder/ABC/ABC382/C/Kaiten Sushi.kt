package AtCoder.ABC.ABC382.C

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt).mapIndexed { index, i -> index to i }
    val B = readln().trim().split(" ").map(String::toInt)
    val aList = mutableListOf<Int>()
    val aIdxList = mutableListOf<Int>()
    for ((aIdx, a) in A) {
        if (aList.isEmpty() || aList.last() > a) {
            aList.add(a)
            aIdxList.add(aIdx)
        }
    }
    aList.reverse()
    aIdxList.reverse()
    val sb = StringBuilder()
    for (b in B) {
        val idx = aList.binarySearch(b + 1).let { if (it >= 0) it else -it - 1 } - 1
        sb.appendLine(if (idx == -1) -1 else (aIdxList[idx] + 1))
    }
    println(sb)
}