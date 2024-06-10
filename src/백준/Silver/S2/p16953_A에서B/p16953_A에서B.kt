package 백준.Silver.S2.p16953_A에서B

import java.util.StringTokenizer

fun main() {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val from = st.nextToken().toInt()
    val to = st.nextToken().toInt()
    println(changeCount(from, to))
}

fun changeCount(a: Int, b: Int): Int {
    val queue = ArrayDeque<Int>()
    queue.add(a)
    var count = 1
    while (queue.isNotEmpty()) {
        count++
        for (i in 0 until queue.size) {
            val num = queue.removeFirst()
            if (num.toLong() * 2 <= b) {
                if (num * 2 == b) return count
                queue.add(num * 2)
            }
            if (num.toLong() * 10 + 1 <= b) {
                if (num * 10 + 1 == b) return count
                queue.add(num * 10 + 1)
            }
        }
    }

    return -1
}

