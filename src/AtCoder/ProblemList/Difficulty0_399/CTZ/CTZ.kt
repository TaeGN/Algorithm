package AtCoder.ProblemList.Difficulty0_399.CTZ

fun main() {
    fun result(): Int {
        val str = readln().trim().toInt().toString(2)
        var count = 0
        for (i in (str.length - 1) downTo 0) {
            if (str[i] == '0') count++
            else break
        }
        return count
    }
    println(result())
}