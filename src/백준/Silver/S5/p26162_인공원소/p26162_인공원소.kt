package 백준.Silver.S5.p26162_인공원소

fun main() {
    val isNotPrime = BooleanArray(116)
    val primeList = mutableListOf<Int>()
    for (i in 2 until isNotPrime.size) {
        if (isNotPrime[i]) continue
        primeList.add(i)
        for (j in (i * i) until isNotPrime.size step i) {
            isNotPrime[j] = true
        }
    }
    val set = mutableSetOf<Int>()
    for (prime1 in primeList) {
        for (prime2 in primeList) {
            set.add(prime1 + prime2)
        }
    }
    val sb = StringBuilder()
    repeat(readln().toInt()) { sb.appendLine(if (readln().toInt() in set) "Yes" else "No") }
    println(sb)
}