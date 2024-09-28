package 백준.Gold.G4.p24956_나는정말휘파람을못불어

const val MOD = 1_000_000_007
fun main() {
    val N = readln().toInt()
    val S = readln()
    val wIdxList = mutableListOf<Int>()
    val hIdxList = mutableListOf<Int>()
    val eCountArr = IntArray(N)
    for (i in (N - 1) downTo 0) {
        eCountArr[i] = eCountArr.getOrElse(i + 1) { 0 } + if (S[i] == 'E') 1 else 0
    }
    val pow2 = IntArray(eCountArr[0] + 1).apply { this[0] = 1 }
    for (i in 1 until pow2.size) {
        pow2[i] = pow2[i - 1] * 2 % MOD
    }
    for (i in eCountArr.indices) {
        eCountArr[i] = (pow2[eCountArr[i]] + MOD - eCountArr[i] - 1) % MOD
    }
    for (i in S.indices) {
        if (S[i] == 'W') wIdxList.add(i)
        else if (S[i] == 'H') hIdxList.add(i)
    }
    val countArrByHIdx = IntArray(hIdxList.size)
    for (i in (hIdxList.size - 1) downTo 0) {
        val hIdx = hIdxList[i]
        countArrByHIdx[i] = (countArrByHIdx.getOrElse(i + 1) { 0 } + eCountArr.getOrElse(hIdx + 1) { 0 }) % MOD
    }
    var result = 0
    for (wIdx in wIdxList) {
        val i = hIdxList.binarySearch(wIdx + 1).let { if (it >= 0) it else -it - 1 }
        if (i == hIdxList.size) continue
        result = (result + countArrByHIdx[i]) % MOD
    }
    println(result)
}