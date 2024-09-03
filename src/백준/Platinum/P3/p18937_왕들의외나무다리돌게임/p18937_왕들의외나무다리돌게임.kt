package 백준.Platinum.P3.p18937_왕들의외나무다리돌게임

fun main() {
    val N = readln().toInt()
    val aArr = readln().trim().split(" ").map(String::toInt)
    val king = readln()
    val otherKing = if (king == "Whiteking") "Blackking" else "Whiteking"
    println(if (aArr.fold(0) { acc, i -> acc xor (i - 2) } != 0) king else otherKing)
}