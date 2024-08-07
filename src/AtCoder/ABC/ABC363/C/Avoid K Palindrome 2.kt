package AtCoder.ABC.ABC363.C

fun main() {
    fun StringBuilder.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    fun String.nextPermutation(): String? {
        val sb = StringBuilder(this)
        var i = length - 1
        while (i > 0 && this[i - 1] >= this[i]) i--
        if (i == 0) return null

        var j = length - 1
        while (j > i - 1 && this[i - 1] >= this[j]) j--
        sb.swap(i - 1, j)

        var k = length - 1
        while (i < k) {
            sb.swap(i++, k--)
        }
        return sb.toString()
    }

    fun String.containPalindrome(k: Int): Boolean {
        for (i in 0..length - k) {
            var isPalindrome = true
            for (j in 0..k / 2) {
                if (this[i + j] != this[i + k - 1 - j]) {
                    isPalindrome = false
                    break
                }
            }
            if (isPalindrome) return true
        }
        return false
    }

    val (N, K) = readln().split(" ").map(String::toInt).let { it[0] to it[1] }
    var S: String? = readln().asSequence().sorted().joinToString("")
    var count = 0
    while (S != null) {
        if (!S.containPalindrome(K)) count++
        S = S.nextPermutation()
    }

    println(count)
}