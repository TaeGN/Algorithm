package AtCoder.ABC.ABC353.E

const val MOD = (1L shl 61) - 1
fun main() {
    val N = readln().toInt()
    val sArr = readln().trim().split(" ")
    val map = hashMapOf<Long, Int>()
    val hashArr = LongArray(3 * 26 * 100000) { (1 until MOD).random() }
    var result = 0L
    for (s in sArr) {
        var hash = 0L
        for ((idx, c) in s.withIndex()) {
            hash = (hash + hashArr[idx * 26 + (c - 'a')]) % MOD
            result += map.getOrDefault(hash, 0)
            map.compute(hash) { _, v -> if (v == null) 1 else v + 1 }
        }
    }
    println(result)
}