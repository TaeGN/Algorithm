package AtCoder.ProblemList.Difficulty400_799.ChristmasTrees

fun main() {
    val (A, M, L, R) = readln().trim().split(" ").map(String::toLong)
    val a = (A % M + M) % M
    val l = (L % M + M) % M
    val r = (R % M + M) % M
    val ll = L + (M + a - l) % M
    val rr = R - (M + r - a) % M
    println(if (ll <= rr) ((rr - ll) / M + 1) else 0)
}