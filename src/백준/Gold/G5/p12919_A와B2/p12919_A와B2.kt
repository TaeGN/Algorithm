package 백준.Gold.G5.p12919_A와B2

fun main() {
    val S = readln()
    val T = readln()
    fun isPossible(): Boolean {
        if (S.length > T.length) return false
        val queue = ArrayDeque<String>()
        queue.add(T)
        while (queue.isNotEmpty()) {
            val curStr = queue.removeFirst()
            if (curStr.length == S.length) {
                if (curStr == S) return true
                else continue
            }
            if (curStr.last() == 'A') queue.add(curStr.substring(0, curStr.length - 1))
            if (curStr.first() == 'B') queue.add(curStr.substring(1).reversed())
        }
        return false
    }
    println(if (isPossible()) 1 else 0)
}