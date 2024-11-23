package AtCoder.ABC.ABC381.C

fun main() {
    val N = readln().trim().toInt()
    val S = readln().trim().split("/")
    val oneCountArr = IntArray(S.size)
    val twoCountArr = IntArray(S.size)
    for (i in S.indices) {
        val str = S[i]
        var oneCount = 0
        var twoCount = 0
        while (twoCount < str.length && str[twoCount] == '2') twoCount++
        while (oneCount < str.length && str[str.length - 1 - oneCount] == '1') oneCount++
        oneCountArr[i] = oneCount
        twoCountArr[i] = twoCount
    }
    var result = 0
    for (i in 0 until (S.size - 1)) {
        result = maxOf(result, minOf(oneCountArr[i], twoCountArr[i + 1]) * 2 + 1)
    }
    println(result)
}