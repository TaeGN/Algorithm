package AtCoder.ABC.ABC365.A

fun main() {
    fun day(year: Int): Int = when {
        year % 4 != 0 -> 365
        year % 100 != 0 -> 366
        year % 400 != 0 -> 365
        else -> 366
    }
    println(day(readln().toInt()))
}