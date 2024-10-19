package AtCoder.ProblemList.Difficulty800_1199.PopcountAndXOR

fun main() {
    var (a, b, C) = readln().trim().split(" ").let { Triple(it[0].toInt(), it[1].toInt(), it[2].toLong()) }
    fun result(): String {
        var A = 0L
        var B = 0L
        for (i in 0 until 60) {
            if ((C and (1L shl i)) != 0L) {
                if (a >= b) {
                    A = A or (1L shl i)
                    a--
                } else {
                    B = B or (1L shl i)
                    b--
                }
            }
        }
        if (a != b) return "-1"
        for (i in 0 until 60) {
            if ((C and (1L shl i)) == 0L && a > 0 && b > 0) {
                A = A or (1L shl i)
                B = B or (1L shl i)
                a--
                b--
            }
        }
        if (a != 0 || b != 0) return "-1"
        return "$A $B"
    }
    println(result())
}