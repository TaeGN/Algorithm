package 백준.Gold.G4.p2631_줄세우기

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val N = readln().toInt()
    val arr = IntArray(N) { readln().toInt() }
    val lis = IntArray(N + 1) { IMPOSSIBLE }
    for (a in arr) {
        lis[lis.binarySearch(a).let { if (it >= 0) it else -it - 1 }] = a
    }
    println(N - lis.indexOf(IMPOSSIBLE))
}