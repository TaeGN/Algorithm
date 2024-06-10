package 백준.Silver.S1.p30892_상어키우기

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.min

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val numberOfSharks = st.nextToken().toInt()
    val maxEatCount = st.nextToken().toInt()
    var babySharkSize = st.nextToken().toLong()
    val sizeOfSharks = readLine().split(" ").map { it.toLong() }.sorted().toMutableList()
    close()

    for (i in 0 until min(numberOfSharks, maxEatCount)) {
        val _index = sizeOfSharks.binarySearch(babySharkSize)
        val index = if (_index >= 0) _index - 1 else -(_index + 2)
        if (index < 0) break
        babySharkSize += sizeOfSharks[index]
        sizeOfSharks.removeAt(index)
    }

    println(babySharkSize)
}