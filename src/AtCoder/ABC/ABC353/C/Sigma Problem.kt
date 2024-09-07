package AtCoder.ABC.ABC353.C

const val MOD = 100000000
fun main() {
    val N = readln().toInt()
    val aArr = readln().trim().split(" ").map(String::toInt).sorted().toIntArray()
    var result = 0L
    var j = N
    for (i in 0 until N) {
        result += aArr[i].toLong() * (N - 1)
        j = maxOf(i + 1, j)
        while (j - 1 > i && aArr[i] + aArr[j - 1] >= MOD) j--
        result -= MOD.toLong() * (N - j)
    }
    println(result)
}