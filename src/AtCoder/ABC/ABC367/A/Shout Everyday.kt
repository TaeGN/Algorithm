package AtCoder.ABC.ABC367.A

fun main() {
    val str = readln()
    var lastIdx = str.length - 1
    while (str[lastIdx] == '0') lastIdx--
    if (str[lastIdx] == '.') lastIdx--
    println(str.substring(0, lastIdx + 1))
}