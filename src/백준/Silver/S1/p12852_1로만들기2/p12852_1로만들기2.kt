package 백준.Silver.S1.p12852_1로만들기2

fun main() {
    val n = readln().toInt()
    val queue = ArrayDeque<Int>()
    val visited = IntArray(n + 1)
    fun ArrayDeque<Int>.visit(prevNum: Int, nextNum: Int) {
        if (nextNum == 0 || visited[nextNum] > 0) return
        visited[nextNum] = prevNum
        add(nextNum)
    }

    fun minCount(): Int {
        queue.add(n)
        var count = 0
        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val num = queue.removeFirst()
                if (num == 1) return count
                if (num % 3 == 0) queue.visit(num, num / 3)
                if (num % 2 == 0) queue.visit(num, num / 2)
                queue.visit(num, num - 1)
            }
            count++
        }
        return count
    }

    val sb = StringBuilder()
    println(minCount())
    var i = 1
    while (i < n) {
        sb.insert(0, "$i ")
        i = visited[i]
    }
    sb.insert(0, "$n ")
    println(sb)
}