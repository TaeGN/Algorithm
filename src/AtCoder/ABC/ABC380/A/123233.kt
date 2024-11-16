package AtCoder.ABC.ABC380.A

fun main() {
    val map = readln().trim().groupingBy { it }.eachCount()
    fun result(): String {
        if (map.getOrDefault('1', 0) != 1) return "No"
        if (map.getOrDefault('2', 0) != 2) return "No"
        if (map.getOrDefault('3', 0) != 3) return "No"
        return "Yes"
    }
    println(result())
}