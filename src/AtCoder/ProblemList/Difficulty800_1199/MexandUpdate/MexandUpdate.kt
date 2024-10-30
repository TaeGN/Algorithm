package AtCoder.ProblemList.Difficulty800_1199.MexandUpdate

import java.util.TreeSet

fun main() {
    val (N, Q) = readln().trim().split(" ").map(String::toInt)
    val mex = TreeSet<Int>()
    val countArr = IntArray(N + 1)
    val A = readln().trim().split(" ").map(String::toInt).toIntArray()
    A.forEach { if (it <= N) countArr[it]++ }
    countArr.forEachIndexed { index, i -> if (i == 0) mex.add(index) }
    val sb = StringBuilder()
    repeat(Q) {
        val (i, x) = readln().trim().split(" ").map(String::toInt)
        if (A[i - 1] != x) {
            if (A[i - 1] <= N && --countArr[A[i - 1]] == 0) mex.add(A[i - 1])
            if (x <= N && ++countArr[x] == 1) mex.remove(x)
            A[i - 1] = x
        }
        sb.appendLine(mex.first())
    }
    println(sb)
}