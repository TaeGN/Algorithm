package AtCoder.ProblemList.TetrahedralNumber

fun main() {
    val N = readln().trim().toInt()
    val sb = StringBuilder()
    fun dfs(idx: Int = 0, arr: IntArray = IntArray(3)) {
        if (idx == 3) {
            sb.appendLine(arr.joinToString(" "))
            return
        }
        for (i in 0..N) {
            arr[idx] = i
            if (arr.sum() <= N) dfs(idx + 1, arr)
        }
        arr[idx] = 0
    }
    dfs()
    println(sb)
}