package 백준.Gold.G4.p1744_수묶기

fun main() {
    val N = readln().toInt()
    val mList = mutableListOf<Int>()
    val pList = mutableListOf<Int>()
    var result = 0L
    repeat(N) { readln().toInt().let { if (it == 1) result += 1 else if (it > 1) pList.add(it) else mList.add(it) } }
    mList.sort()
    pList.sortDescending()
    for (i in 0 until mList.size step 2) {
        result += mList[i] * mList.getOrElse(i + 1) { 1 }
    }
    for (i in 0 until pList.size step 2) {
        result += pList[i] * pList.getOrElse(i + 1) { 1 }
    }
    println(result)
}