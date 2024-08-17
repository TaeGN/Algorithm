package 백준.Platinum.P4.p11868_님게임2

fun main() {
    val N = readln().toInt()
    val result = readln().split(" ").map(String::toInt).reduce { acc, i -> acc xor i }
    println(if (result > 0) "koosaga" else "cubelover")
}