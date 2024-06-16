package 백준.Silver.S2.p15666_N과M12

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map(String::toInt).let { it[0] to it[1] }
    val numList = br.readLine().split(" ").asSequence().distinct().map(String::toInt).sorted().toList()

    fun duplicateCombination(m: Int, numList: List<Int>): String {
        val sb = StringBuilder()
        fun duplicateCombination(numListIdx: Int = 0, countDown: Int = m, selectedCountArr: IntArray = IntArray(numList.size)) {
            if (countDown == 0) {
                selectedCountArr.forEachIndexed { index, i -> sb.append("${numList[index]} ".repeat(i)) }
                sb.appendLine()
                return
            }
            if (numListIdx == numList.size) return

            for (count in countDown downTo 0) {
                selectedCountArr[numListIdx] = count
                duplicateCombination(numListIdx + 1, countDown - count, selectedCountArr)
            }
        }
        duplicateCombination()
        return sb.toString()
    }

    println(duplicateCombination(m, numList))
}