package 백준.Silver.S4.p11047_동전0

fun main() = with(System.`in`.bufferedReader()){
    val (N, K) = readLine().split(" ").let { it[0].toInt() to it[1].toInt() }
    val priceArr = IntArray(N)
    repeat(N) {idx ->
        priceArr[idx] = readLine().toInt()
    }

    var sumPrice = K
    var minCount = 0
    for(price in priceArr.reversed()) {
        minCount += sumPrice / price
        sumPrice %= price
    }

    println(minCount)
}