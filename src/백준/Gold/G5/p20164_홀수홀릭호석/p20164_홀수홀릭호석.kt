package 백준.Gold.G5.p20164_홀수홀릭호석

fun main() {
    val n = System.`in`.bufferedReader().readLine().toInt()
    fun Int.oddCount(): Int = this.toString().count { it.digitToInt() % 2 == 1 }
    fun Int.length(): Int = this.toString().length
    fun operation(
        num: Int,
        comparator: Comparator<Int>,
        operationMap: MutableMap<Int, Int> = mutableMapOf()
    ): Int = operationMap[num] ?: when (num.length()) {
        1 -> num.oddCount()
        2 -> operation(
            num.toString().asSequence().map(Char::digitToInt).sum(),
            comparator,
            operationMap
        ) + num.oddCount()

        else -> {
            val length = num.length()
            val numStr = num.toString()
            var result = if (comparator.compare(Int.MIN_VALUE, Int.MAX_VALUE) > 0) Int.MIN_VALUE else Int.MAX_VALUE
            for (count1 in 1..(length - 2)) {
                for (count2 in 1..(length - 1 - count1)) {
                    val n1 = numStr.substring(0, count1).toInt()
                    val n2 = numStr.substring(count1, count1 + count2).toInt()
                    val n3 = numStr.substring(count1 + count2, length).toInt()
                    val op = operation(n1 + n2 + n3, comparator, operationMap)
                    if (comparator.compare(result, op) > 0) {
                        result = op
                    }
                }
            }
            result + num.oddCount()
        }
    }.also { operationMap[num] = it }

    val sb = StringBuilder()
    operation(n, Comparator.naturalOrder()).let(sb::append)
    sb.append(" ")
    operation(n, Comparator.reverseOrder()).let(sb::append)
    println(sb)
}