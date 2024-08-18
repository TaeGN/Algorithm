package 백준.Platinum.P4.p11869_님블

fun main() {
    val M = readln().toInt()
    var result = 0
    readln().split(" ").map(String::toInt).forEach { result = result xor it }
    println(if (result != 0) "koosaga" else "cubelover")
}