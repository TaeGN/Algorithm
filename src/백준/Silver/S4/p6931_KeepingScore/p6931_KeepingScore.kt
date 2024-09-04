package 백준.Silver.S4.p6931_KeepingScore

fun main() {
    val S = readln()
    val typeArr = charArrayOf('C', 'D', 'H', 'S')
    val typeNameArr = arrayOf("Clubs", "Diamonds", "Hearts", "Spades")
    val idxArr = IntArray(5) { if (it < 4) S.indexOf(typeArr[it]) + 1 else S.length + 1 }
    val pointArr = IntArray(4)
    val sbArr = Array(4) { StringBuilder(typeNameArr[it]) }
    for (i in 0 until 4) {
        for (j in idxArr[i] until idxArr[i + 1] - 1) {
            sbArr[i].append(" ${S[j]}")
            pointArr[i] += when (S[j]) {
                'A' -> 4
                'K' -> 3
                'Q' -> 2
                'J' -> 1
                else -> 0
            }
        }
        pointArr[i] += maxOf(0, 3 - (idxArr[i + 1] - idxArr[i] - 1))
    }
    val totalPoint = pointArr.sum()
    val len = sbArr.maxOf { it.length } + 12
    for (i in 0 until 4) {
        if (pointArr[i] >= 10) sbArr[i].append(" ".repeat(len - sbArr[i].length - 2)).append(pointArr[i])
        else sbArr[i].append(" ".repeat(len - sbArr[i].length - 1)).append(pointArr[i])
    }
    println("Cards Dealt${" ".repeat(len - 17)}Points")
    println(sbArr.joinToString("\n"))
    println("${" ".repeat(len - (if (totalPoint >= 10) 8 else 7))}Total $totalPoint")
}