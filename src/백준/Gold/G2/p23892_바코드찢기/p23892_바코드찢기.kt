package 백준.Gold.G2.p23892_바코드찢기

fun main() {
    fun count(S: String, P: Int): Long {
        var count = 0L
        for (i in 0 until S.length - 1) {
            if (S[i] == '(' && S[i + 1] == ')') count += P
        }
        if (S.first() == ')' && S.last() == '(') count += (P - 1)
        return count
    }
    val (N, K) = readln().split(" ").map(String::toLong)
    val myCodeCount = readln().split(" ").let { count(it[0], it[1].toInt()) }
    val drinkCodeCount = readln().split(" ").let { count(it[0], it[1].toInt()) }
    fun result(): Long {
        if (myCodeCount < K) return 0L
        if (drinkCodeCount >= K) return N
        return minOf(N, (myCodeCount - K) / (K - drinkCodeCount) + 1)
    }
    println(result())
}