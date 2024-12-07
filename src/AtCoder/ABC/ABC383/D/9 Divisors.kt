package AtCoder.ABC.ABC383.D

fun main() {
    val primeList = mutableListOf<Int>()
    val isNotPrime = BooleanArray(1000001).apply { this[0] = true; this[1] = true }
    for (i in isNotPrime.indices) {
        if (isNotPrime[i]) continue
        primeList.add(i)
        if (i.toLong() * i >= isNotPrime.size) continue
        for (j in (i * i) until isNotPrime.size step i) {
            isNotPrime[j] = true
        }
    }
    val N = readln().trim().toLong()
    var result = 0
    for (i in primeList.indices) {
        val square = primeList[i].toLong() * primeList[i]
        if (square > N) break
        for (j in (i + 1) until primeList.size) {
            if (square * primeList[j] > N) break
            if (square * primeList[j] * primeList[j] > N) break
            result++
        }
    }
    a@ for (i in primeList.indices) {
        var value = 1L
        for (j in 0 until 8) {
            value *= primeList[i]
            if (value > N) break@a
        }
        result++
    }
    println(result)
}