package AtCoder.ProblemList.Difficulty0_399.ColorfulBeans

fun main() {
    val N = readln().trim().toInt()
    val beans = Array(N) { readln().trim().split(" ").map(String::toInt) }.groupBy { it[1] }
    println(beans.maxOf { (_, value) -> value.minOf { it[0] } })
}