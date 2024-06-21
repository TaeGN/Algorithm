package 백준.Gold.G2.p20210_파일탐색기

import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val regex = """([a-zA-Z]+|[0-9]+)""".toRegex()
    val sortSequenceMap = mutableMapOf<Char, Int>()
    ('0'..'9').forEachIndexed { index, c -> sortSequenceMap[c] = index }
    ('A'..'Z').forEachIndexed { index, c -> sortSequenceMap[c] = (index * 2) + 10 }
    ('a'..'z').forEachIndexed { index, c -> sortSequenceMap[c] = (index * 2 + 1) + 10 }
    val comparator = Comparator<List<String>> { matchList1, matchList2 ->
        for (i in 0 until min(matchList1.size, matchList2.size)) {
            val str1 = matchList1[i].trimStart('0').ifEmpty { "0" }
            val str2 = matchList2[i].trimStart('0').ifEmpty { "0" }

            if (str1[0].isDigit() && str2[0].isDigit()) {
                if (str1.length != str2.length) return@Comparator str1.length.compareTo(str2.length)
                if (str1.toDouble() != str2.toDouble()) return@Comparator str1.toDouble().compareTo(str2.toDouble())
//            같은 숫자이면서 앞에 붙는 0의 개수가 다른 경우
                if (matchList1[i].length != matchList2[i].length)
                    return@Comparator matchList1[i].length.compareTo(matchList2[i].length)
            }

            for (j in 0 until min(str1.length, str2.length)) {
                if (sortSequenceMap[str1[j]] == sortSequenceMap[str2[j]]) continue
                return@Comparator sortSequenceMap[str1[j]]!!.compareTo(sortSequenceMap[str2[j]]!!)
            }

//            문자열 길이가 다른 경우
            if (str1.length != str2.length) return@Comparator str1.length.compareTo(str2.length)
        }

        matchList1.size.compareTo(matchList2.size)
    }

    val n = readLine().toInt()
    val list = mutableListOf<List<String>>()
    repeat(n) {
        list.add(regex.findAll(readLine()).map { it.value }.toList())
    }
    list.sortWith(comparator)
    println(list.joinToString("\n") { it.joinToString("") })
}