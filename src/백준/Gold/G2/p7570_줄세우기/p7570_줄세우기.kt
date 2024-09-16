package 백준.Gold.G2.p7570_줄세우기

fun main() {
    val N = readln().toInt()
    val idxArr = IntArray(N + 1)
    readln().split(" ").map(String::toInt).forEachIndexed { index, i -> idxArr[i] = index }
    val countArr = IntArray(N + 1) { 1 }
    for (i in (N - 1) downTo 1) {
        if (idxArr[i] < idxArr[i + 1]) countArr[i] += countArr[i + 1]
    }
    println(N - countArr.max())
}