package AtCoder.ABC.ABC368.B

fun main() {
    val N = readln().toInt()
    val arr = readln().split(" ").map(String::toInt).sortedDescending().toIntArray()
    var count = 0
    while (arr[1] > 0) {
        count++
        arr[0] -= 1
        arr[1] -= 1
        arr.sortDescending()
    }
    println(count)
}