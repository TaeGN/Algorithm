package 백준.Platinum.P5.p3015_오아시스재결합

fun main() {
    val N = readln().toInt()
    val stack = ArrayDeque<Pair<Int, Int>>()
    var result = 0L
    repeat(N) {
        val num = readln().toInt()
        var count = 1
        while (stack.isNotEmpty() && stack.first().first <= num) {
            if (stack.first().first == num) count += stack.first().second
            result += stack.removeFirst().second
        }
        if (stack.isNotEmpty()) result++
        stack.addFirst(num to count)
    }
    println(result)
}