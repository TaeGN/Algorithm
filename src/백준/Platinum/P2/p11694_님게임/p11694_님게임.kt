package 백준.Platinum.P2.p11694_님게임

fun main() {
    val N = readln().toInt()
    val P = readln().split(" ").map(String::toInt)
    fun result(): Boolean {
        val oneCount = P.count { it == 1 }
        if (oneCount == N) return oneCount % 2 == 0
        val result = P.fold(0) { acc, i -> acc xor i }
        if (oneCount == 0 || oneCount % 2 == 1) return result != 0
        for (p in P) {
            if (p != 1 && ((result xor p) xor 1) != 0) return true
        }
        return false
    }
    println(if (result()) "koosaga" else "cubelover")
}