package AtCoder.ABC.ABC376.C

fun main() {
    val N = readln().trim().toInt()
    val A = readln().trim().split(" ").map(String::toInt).sorted()
    val B = readln().trim().split(" ").map(String::toInt).sorted()
    fun result(): Int {
        for (i in (N - 1) downTo 1) {
            if (A[i] > B[i - 1]) {
                for (j in 0 until i) {
                    if (A[j] > B[j]) return -1
                }
                return A[i]
            }
        }
        return A[0]
    }
    println(result())
}