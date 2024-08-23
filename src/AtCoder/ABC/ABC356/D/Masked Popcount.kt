package AtCoder.ABC.ABC356.D

const val MOD = 998244353
fun main() {
    val (N, M) = readln().split(" ").map(String::toLong)
    var result = 0L
    for (i in 0..60) {
        if ((M and (1L shl i)) != 0L) {
            if ((N and (1L shl i)) != 0L) result = (result + (N and ((1L shl i) - 1)) + 1) % MOD
            result = (result + ((N shr (i + 1)) * (1L shl i) % MOD)) % MOD
        }
    }
    println(result)
}