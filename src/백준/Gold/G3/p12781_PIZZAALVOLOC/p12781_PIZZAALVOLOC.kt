package 백준.Gold.G3.p12781_PIZZAALVOLOC

fun main() {
    fun ccw(xArr: IntArray, yArr: IntArray): Long {
        var result = 0L
        for (i in 0 until 3) {
            result += xArr[i] * yArr[(i + 1) % 3]
            result -= xArr[(i + 1) % 3] * yArr[i]
        }
        return result
    }

    fun ccw4(xArr: IntArray, yArr: IntArray): Int {
        val ccw1 = ccw(intArrayOf(xArr[0], xArr[1], xArr[2]), intArrayOf(yArr[0], yArr[1], yArr[2]))
        val ccw2 = ccw(intArrayOf(xArr[0], xArr[1], xArr[3]), intArrayOf(yArr[0], yArr[1], yArr[3]))
        return if (ccw1 * ccw2 < 0) return 1
        else 0
    }

    val arr = readln().split(" ").map(String::toInt)
    val xArr = arr.filterIndexed { index, _ -> index % 2 == 0 }.toIntArray()
    val yArr = arr.filterIndexed { index, _ -> index % 2 == 1 }.toIntArray()
    println(ccw4(xArr, yArr))
}