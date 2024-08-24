package AtCoder.ABC.ABC368.C

fun main() {
    val N = readln().toInt()
    val hArr = readln().split(" ").map(String::toInt).toIntArray()
    var result = 0L
    var t = 0
    for (h in hArr) {
        result += h / 5 * 3
        var remainedH = h % 5
        while (remainedH > 0) {
            remainedH -= if (t == 2) 3 else 1
            t = (t + 1) % 3
            result++
        }
    }
    println(result)
}