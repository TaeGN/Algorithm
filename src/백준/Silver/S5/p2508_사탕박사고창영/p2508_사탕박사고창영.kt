package 백준.Silver.S5.p2508_사탕박사고창영

const val MAX_R = 400
const val MAX_C = 400
fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    val map = List(MAX_R) { CharArray(MAX_C) }
    val sb = StringBuilder()
    repeat(t) {
        readLine()
        val (r, c) = readLine().split(" ").map(String::toInt).let { it[0] to it[1] }
        repeat(r) {rIdx ->
            for((cIdx, value) in readLine().withIndex()) {
                map[rIdx][cIdx] = value
            }
        }

        var count = 0
        for(rIdx in 0 until r) {
            for(cIdx in 0 until c) {
                when(map[rIdx][cIdx]) {
                    '>' -> if(cIdx + 2 < c && map[rIdx][cIdx + 1] ==  'o' && map[rIdx][cIdx + 2] ==  '<') count++
                    'v' -> if(rIdx + 2 < r && map[rIdx + 1][cIdx] ==  'o' && map[rIdx + 2][cIdx] ==  '^') count++
                }
            }
        }
        sb.appendLine(count)
    }

    println(sb)
}