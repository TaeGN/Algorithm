package 백준.Gold.G3.p9028_Iris_비밀번호

const val MAX_WORD_LENGTH = 10
const val IMPOSSIBLE = Int.MAX_VALUE

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val queueList = List(MAX_WORD_LENGTH) { ArrayDeque<Int>() }
    val T = readLine().toInt()
    repeat(T) {
        queueList.forEach { it.clear() }
        val wordMap = readLine().mapIndexed { index, c -> (if (c.isDigit()) c else c.lowercaseChar()) to index }.toMap()
        for ((curIdx, c) in readLine().withIndex()) {
            val elm = if (c.isDigit()) c else c.lowercaseChar()
            if (elm in wordMap) {
                val queueListIdx = wordMap[elm]!!
                val prevIdx = if (queueListIdx > 0) queueList[queueListIdx - 1].firstOrNull() ?: IMPOSSIBLE else -1
                if (prevIdx < curIdx) {
                    if (queueListIdx > 0) queueList[queueListIdx - 1].removeFirstOrNull()
                    queueList[queueListIdx].add(curIdx)
                }
            }
        }
        sb.append(queueList[wordMap.size - 1].size)
        for (idx in queueList[wordMap.size - 1].take(3)) {
            sb.append(" ${idx + 1}")
        }
        sb.appendLine()
    }
    println(sb)
}