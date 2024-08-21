package AtCoder.ABC.ABC357.B

fun main() {
    val S = readln()
    val upperCount = S.count { it.isUpperCase() }
    val lowerCount = S.length - upperCount
    println(if (upperCount > lowerCount) S.uppercase() else S.lowercase())
}