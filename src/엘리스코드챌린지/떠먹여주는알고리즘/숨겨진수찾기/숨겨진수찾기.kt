package 엘리스코드챌린지.떠먹여주는알고리즘.숨겨진수찾기

fun main() {
    fun String.sum(): Long {
        var sum: Long = 0
        var num: Long = 0
        for (c in this) {
            if (c.isDigit()) {
                num = num * 10 + c.digitToInt()
            } else {
                sum += num
                num = 0
            }
        }
        sum += num
        return sum
    }
    println(readln().sum())
}