package 백준.Bronze.B4.p30033_RustStudy

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val plans = br.readLine().split(" ").map(String::toInt)
    val records = br.readLine().split(" ").map(String::toInt)
    plans.filterIndexed { index, plan -> plan <= records[index] }.count().let(::println)
}