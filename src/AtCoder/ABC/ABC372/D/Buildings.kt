package AtCoder.ABC.ABC372.D
fun main() {
    val N = readln().toInt()
    val H = readln().split(" ").map(String::toInt)
    val countArr = LongArray(N)
    val stack = ArrayDeque<Int>()
    for (i in (N - 1) downTo 0) {
        val h = H[i]
        while (stack.isNotEmpty() && stack.first() < h) {
            countArr[i]++
            stack.removeFirst()
        }
        countArr[i] += stack.size.toLong()
        stack.addFirst(h)
    }
    println(countArr.joinToString(" "))
}