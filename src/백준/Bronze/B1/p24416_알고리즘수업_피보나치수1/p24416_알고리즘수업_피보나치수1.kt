package 백준.Bronze.B1.p24416_알고리즘수업_피보나치수1

import kotlin.math.max

fun main() {
    val n = readln().toInt()
    var count = 0
    fun fib(n: Int): Int = if (n == 1 || n == 2) 1.also { count++ } else fib(n - 1) + fib(n - 2)
    fib(n)
    println("$count ${max(0, n - 2)}")
}