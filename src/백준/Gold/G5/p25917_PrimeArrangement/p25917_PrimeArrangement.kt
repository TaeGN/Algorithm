package 백준.Gold.G5.p25917_PrimeArrangement

const val MOD = 998_244_353
fun main() = with(System.`in`.bufferedReader()) {
    fun Int.powByMod(count: Int, powByModMap: MutableMap<Int, Int> = mutableMapOf(1 to this)): Int {
        require(count > 0)
        if (powByModMap.containsKey(count)) return powByModMap[count]!!
        return (if (count % 2 == 1) (this.toLong() * this.powByMod(
            count / 2,
            powByModMap
        ) % MOD * this.powByMod(count / 2, powByModMap) % MOD).toInt()
        else (powByMod(count / 2).toLong() * powByMod(
            count / 2,
            powByModMap
        ) % MOD).toInt()).also { powByModMap[count] = it }
    }

    val (R, C) = readLine().split(" ").let { it[0].toInt() to it[1].toInt() }
    readLine()
    readLine()

    val factorialArr = IntArray(R * C + 1) { 1 }
    factorialArr.forEachIndexed { index, _ ->
        if (index > 0) factorialArr[index] = (factorialArr[index - 1].toLong() * index % MOD).toInt()
    }

    val top = factorialArr[R * C]
    val bottom = factorialArr[R]
    println((top.toLong() * bottom.powByMod(MOD - 2)) % MOD)
}