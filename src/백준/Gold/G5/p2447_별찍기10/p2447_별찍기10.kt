package 백준.Gold.G5.p2447_별찍기10

fun main() {
    val starMap = mutableMapOf(1 to listOf("*"))
    fun star(n: Int): List<String> = if (starMap.containsKey(n)) starMap[n]!!
    else star(n / 3).let { sub ->
        sub.map { it.repeat(3) } + sub.map { it + " ".repeat(it.length) + it } + sub.map { it.repeat(3) }
    }.also { starMap[n] = it }

    val N = readln().toInt()
    println(star(N).joinToString("\n"))
}