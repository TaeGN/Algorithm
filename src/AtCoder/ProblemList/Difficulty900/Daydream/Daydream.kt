package AtCoder.ProblemList.Difficulty900.Daydream

fun main() {
    val S = readln()
    fun String.isMatch(str: String, fromIndex: Int): Boolean {
        if (fromIndex < 0 || fromIndex + str.length > length) return false
        for (i in str.indices) {
            if (this[fromIndex + i] != str[i]) return false
        }
        return true
    }

    fun result(): String {
        var i = S.length - 1
        while (i >= 0) {
            if (S[i] == 'r') {
                if (S.isMatch("dreamer", i - 6)) i -= 7
                else if (S.isMatch("eraser", i - 5)) i -= 6
                else return "NO"
            } else {
                if (S.isMatch("dream", i - 4)) i -= 5
                else if (S.isMatch("erase", i - 4)) i -= 5
                else return "NO"
            }
        }
        return "YES"
    }
    println(result())
}