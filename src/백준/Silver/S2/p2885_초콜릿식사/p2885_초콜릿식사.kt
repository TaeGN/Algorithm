package 백준.Silver.S2.p2885_초콜릿식사

fun main() {
    val K = readln().toInt()
    val str = K.toString(2)
    fun result(): String {
        if(1 shl (str.length - 1) == K) return "$K 0"
        return "${1 shl str.length} ${str.lastIndexOf("1") - str.indexOf("1") + 1}"
    }
    println(result())
}