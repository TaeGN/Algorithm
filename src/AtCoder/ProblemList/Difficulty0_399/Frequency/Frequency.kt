package AtCoder.ProblemList.Difficulty0_399.Frequency

fun main() {
    val countArr = IntArray(26)
    readln().trim().forEach { countArr[it - 'a']++ }
    println('a' + countArr.indexOf(countArr.max()))
}