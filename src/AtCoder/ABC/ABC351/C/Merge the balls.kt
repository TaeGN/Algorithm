package AtCoder.ABC.ABC351.C

fun main() {
    val N = readln().toInt()
    val arr = LongArray(N)
    var arrSize = 0
    for (A in readln().split(" ").map(String::toLong)) {
        arr[arrSize++] = A
        while (arrSize > 1 && arr[arrSize - 1] == arr[arrSize - 2]) {
            arr[arrSize - 2] = arr[arrSize - 1] + 1
            arrSize--
        }
    }
    println(arrSize)
}