package AtCoder.ABC.ABC356.E

fun main() {
    val N = readln().toInt()
    val aArr = readln().split(" ").map(String::toInt).sorted()
    val countArr = IntArray(aArr.last() + 1)
    for (a in aArr) {
        countArr[a]++
    }
    for (i in 1 until countArr.size) {
        countArr[i] += countArr[i - 1]
    }
    var result = 0L
    for (num in 1 until countArr.size) {
        val count = countArr[num] - countArr[num - 1]
        if (count == 0) continue
        result += (count - 1).toLong() * count / 2
        for (mulNum in num..aArr.last() step num) {
            val prevCount = countArr[maxOf(num, mulNum - 1)]
            val nextCount = countArr[minOf(mulNum + num - 1, aArr.last())]
            val value = mulNum / num
            result += (nextCount - prevCount).toLong() * value * count
        }
    }
    println(result)
}