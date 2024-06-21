package 백준.Silver.S4.p19844_단어개수세기

fun main() {
    val regex = """^(c|j|n|m|t|s|l|d|qu)'[aeiouh]""".toRegex()
    readln().split(" ", "-").sumOf { (if (it.contains(regex)) 2 else 1).toInt() }.let(::println)
}