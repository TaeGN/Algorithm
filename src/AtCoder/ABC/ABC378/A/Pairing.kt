package AtCoder.ABC.ABC378.A

fun main() {
    println(readln().trim().split(" ").map(String::toInt).groupingBy { it }.eachCount().values.sumOf { it / 2 })
}