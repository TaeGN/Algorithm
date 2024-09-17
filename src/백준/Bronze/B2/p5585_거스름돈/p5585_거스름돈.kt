package 백준.Bronze.B2.p5585_거스름돈

fun main() {
    val arr = intArrayOf(500, 100, 50, 10, 5, 1)
    var money = 1000 - readln().toInt()
    var result = 0
    for (i in arr) {
        result += money / i
        money %= i
    }
    println(result)
}