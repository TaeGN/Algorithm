package 백준.Gold.G5.p2036_수열의점수

fun main() {
    val mList = mutableListOf<Int>()
    val pList = mutableListOf<Int>()
    var result = 0L
    repeat(readln().toInt()) {
        val num = readln().toInt()
        if (num > 1) pList.add(num) else if (num <= 0) mList.add(num) else result++
    }
    mList.sort()
    pList.sortDescending()
    for (i in mList.indices step 2) {
        result += mList[i].toLong() * mList.getOrElse(i + 1) { 1 }
    }
    for (i in pList.indices step 2) {
        result += pList[i].toLong() * pList.getOrElse(i + 1) { 1 }
    }
    println(result)
}