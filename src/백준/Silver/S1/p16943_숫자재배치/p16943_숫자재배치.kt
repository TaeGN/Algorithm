package 백준.Silver.S1.p16943_숫자재배치

fun main() {
    val (A, B) = readln().split(" ")
    val countArr = IntArray(10)
    for (a in A) {
        countArr[a - '0']++
    }
    fun permutation(count: Int = 0, num: String = ""): String {
        if (count == A.length) {
            return if (num[0] != '0' && num.toInt() < B.toInt()) num else "-1"
        }
        for (i in 9 downTo 0) {
            if (countArr[i] == 0) continue
            countArr[i]--
            permutation(count + 1, num + i).let { if (it != "-1") return it }
            countArr[i]++
        }
        return "-1"
    }
    println(permutation())
}