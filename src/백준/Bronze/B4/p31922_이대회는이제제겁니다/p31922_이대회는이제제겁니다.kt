package 백준.Bronze.B4.p31922_이대회는이제제겁니다

import kotlin.math.max

fun main() {
    readln().split(" ").map(String::toInt).let { max(it[1], it[0] + it[2]) }.let(::println)
}