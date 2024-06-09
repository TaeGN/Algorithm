package 백준.Silver.S3.p1996_프린터큐

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val t = readLine().toInt()
    val sb = StringBuilder()
    for(tc in 0 until t) {
        val st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        val documents = readLine().split(" ").map { it.toInt() }.toIntArray()
        sb.append(getImportance(documents, n, m)).append("\n")
    }

    println(sb.toString())
}

fun getImportance(documents: IntArray, n: Int, m: Int) : Int {
    var count = 0
    var currentIdx = -1
    for(importance in 9 downTo 1) {
        val startIdx = currentIdx + 1
        for(idx in startIdx until startIdx + n) {
            if(documents[idx % n] == importance) {
                count++
                currentIdx = idx % n
                if(currentIdx == m) return count
            }
        }
    }

    return count
}