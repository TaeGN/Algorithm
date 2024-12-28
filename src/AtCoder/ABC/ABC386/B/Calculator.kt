package AtCoder.ABC.ABC386.B

fun main() {
    val S = readln().trim()
    var zero = false
    var count = S.length
    for (c in S) {
        if (c == '0') {
            if (zero) count--
            zero = !zero
        } else zero = false
    }
    println(count)
}