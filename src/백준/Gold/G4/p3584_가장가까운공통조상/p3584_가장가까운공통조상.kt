package 백준.Gold.G4.p3584_가장가까운공통조상

const val EMPTY = -1
fun main() {
    val sb = StringBuilder()
    val parents = IntArray(10001)
    val A = IntArray(10001)
    val B = IntArray(10001)
    repeat(readln().toInt()) {
        val N = readln().toInt()
        parents.fill(EMPTY, 1, N + 1)
        repeat(N - 1) { readln().split(" ").map(String::toInt).let { parents[it[1]] = it[0] } }
        var aSize = 0
        var bSize = 0
        var (a, b) = readln().split(" ").map(String::toInt)
        while (a != EMPTY) {
            A[aSize++] = a
            a = parents[a]
        }
        while (b != EMPTY) {
            B[bSize++] = b
            b = parents[b]
        }
        while (aSize > 0 && bSize > 0 && A[aSize - 1] == B[bSize - 1]) {
            aSize--
            bSize--
        }
        sb.appendLine(A[aSize])
    }
    println(sb)
}