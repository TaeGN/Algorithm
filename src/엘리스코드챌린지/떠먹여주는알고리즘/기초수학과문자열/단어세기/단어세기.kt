package 엘리스코드챌린지.떠먹여주는알고리즘.기초수학과문자열.단어세기

fun main() {
    val N = readln().toInt()
    val sb = StringBuilder()
    repeat(N) {
        readln().split(" ").count().let(sb::appendLine)
    }
    println(sb)
}