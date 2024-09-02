package book.etc

fun IntArray.upperBound(value: Int): Int {
    var start = 0
    var end = size
    while (start < end) {
        val mid = (start + end) / 2
        if (value < this[mid]) end = mid
        else start = mid + 1
    }
    return start
}