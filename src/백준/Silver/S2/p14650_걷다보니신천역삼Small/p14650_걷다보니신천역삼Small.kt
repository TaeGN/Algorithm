package 백준.Silver.S2.p14650_걷다보니신천역삼Small

fun main() {
    val n = readln().toInt()
    fun result(idx: Int = 0, sum: Int = 0): Int {
        if(idx == n) return if(sum % 3 == 0) 1 else 0
        var count = 0
        if(idx > 0) count += result(idx + 1, sum)
        count += result(idx + 1, sum + 1)
        count += result(idx + 1, sum + 2)
        return count
    }
    println(result())
}