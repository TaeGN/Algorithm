package AtCoder.ABC.ABC381.D

fun main() {
    val N = readln().trim().toInt()
    val A = readln().trim().split(" ").map(String::toInt)
    val count = IntArray(200001)
    val queue = ArrayDeque<Int>()
    var result = 0
    var i = 0
    while (i < N) {
        if (i < (N - 1) && A[i] == A[i + 1]) {
            if (count[A[i]] != 0) {
                while (count[A[i]] != 0) count[queue.removeFirst()]--
            }
            repeat(2) {
                count[A[i]]++
                queue.add(A[i++])
            }
        } else if (queue.isNotEmpty() && queue.last() == A[i]) {
            while (queue.size > 1) count[queue.removeFirst()]--
            count[A[i]]++
            queue.add(A[i++])
        } else {
            while (queue.isNotEmpty()) count[queue.removeFirst()]--
            i++
        }
        if (queue.size % 2 == 0) result = maxOf(result, queue.size)
    }
    println(result)
}