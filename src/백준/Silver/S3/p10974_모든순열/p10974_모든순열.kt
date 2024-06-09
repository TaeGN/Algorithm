package 백준.Silver.S3.p10974_모든순열

fun main() {
    val n = System.`in`.bufferedReader().readLine().toInt()
    val numList = (1..n).toList()
    val sb = StringBuilder()
    fun permutation(
        count: Int = 0,
        isSelected: BooleanArray = BooleanArray(n),
        selectedArray: IntArray = IntArray(n)
    ) {
        if (count == n) {
            sb.appendLine(selectedArray.joinToString(separator = " "))
            return
        }

        for ((idx, num) in numList.withIndex()) {
            if (isSelected[idx]) continue
            isSelected[idx] = true
            selectedArray[count] = num
            permutation(count + 1, isSelected, selectedArray)
            isSelected[idx] = false
        }
    }

    permutation()
    println(sb)
}