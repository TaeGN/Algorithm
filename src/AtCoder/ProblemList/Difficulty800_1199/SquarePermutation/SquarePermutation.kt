package AtCoder.ProblemList.Difficulty800_1199.SquarePermutation

fun main() {
    val N = readln().trim().toInt()
    val S = readln().trim()
    val countArr = IntArray(10)
    S.forEach { countArr[it - '0']++ }
    val max = "1${"0".repeat(S.length)}".toLong()
    val curCountArr = IntArray(10)
    var i = 0L
    var result = 0L
    while (i * i <= max) {
        curCountArr.fill(0)
        val s = (i * i).toString()
        curCountArr[0] += S.length - s.length
        for (c in s) {
            curCountArr[c - '0']++
        }
        if (countArr.contentEquals(curCountArr)) result++
        i++
    }
    println(result)
}