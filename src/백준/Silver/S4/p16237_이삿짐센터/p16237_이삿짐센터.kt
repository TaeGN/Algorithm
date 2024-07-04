package 백준.Silver.S4.p16237_이삿짐센터

const val MAX_AMOUNT = 5

fun main() {
    val amountList = (arrayListOf(0) + readln().split(" ").map(String::toInt)).toMutableList()
    var count = 0
    for (numA in MAX_AMOUNT downTo 1) {
        while(amountList[numA]-- > 0) {
            var remaining = MAX_AMOUNT - numA
            count++
            for (numB in numA downTo 1) {
                while (remaining >= numB && amountList[numB] > 0) {
                    remaining -= numB
                    amountList[numB]--
                }
            }
        }
    }
    println(count)
}