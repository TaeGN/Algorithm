package AtCoder.ABC.ABC353.A

fun main() {
    val N = readln().toInt()
    val hArr = readln().trim().split(" ").map(String::toInt)
    fun result(): Int {
        for (i in 1 until N) {
            if (hArr[i] > hArr[0]) return i + 1
        }
        return -1
    }

    println(result())
}