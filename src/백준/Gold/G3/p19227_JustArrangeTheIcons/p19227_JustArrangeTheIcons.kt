package 백준.Gold.G3.p19227_JustArrangeTheIcons

fun main() {
    val T = readln().toInt()
    val sb = StringBuilder()
    repeat(T) {
        val N = readln().trim().toInt()
        val countArr = readln().trim().split(" ").asSequence()
            .map(String::toInt).groupBy { it }.map { it.value.size }.sorted().toIntArray()

        fun result(): Int {
            a@ for (i in (countArr[0] + 1) downTo 1) {
                var result = 0
                for (count in countArr) {
                    if (count % i != 0 && (i - count % i) > (count - 1) / i + 1) continue@a
                    result += (count - 1) / i + 1
                }
                return result
            }
            return -1
        }
        sb.appendLine(result())
    }
    println(sb)
}