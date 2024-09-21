package 백준.Bronze.B3.p5751_HeadOrTail

fun main() {
    while (true) {
        val N = readln().toInt()
        if (N == 0) break
        var mary = 0
        var john = 0
        for (i in readln().split(" ").map(String::toInt)) {
            if (i == 0) mary++
            else john++
        }
        println("Mary won $mary times and John won $john times")
    }
}