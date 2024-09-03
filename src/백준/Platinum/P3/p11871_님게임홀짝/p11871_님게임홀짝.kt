package 백준.Platinum.P3.p11871_님게임홀짝

fun main() {
    val N = readln().toInt()
    val pArr = readln().trim().split(" ").map(String::toInt)
    println(if (pArr.fold(0) { acc, i -> acc xor (if (i % 2 == 0) i / 2 - 1 else (i + 1) / 2) } != 0) "koosaga" else "cubelover")
}