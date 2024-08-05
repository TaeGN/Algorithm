package AtCoder.ABC.ABC364.A


fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    var isPossible = true
    var isSweet = false
    repeat(N) { idx ->
        val curIsSweet = readLine() == "sweet"
        if (isSweet && curIsSweet && idx < N - 1) isPossible = false
        isSweet = curIsSweet
    }
    println(if (isPossible) "Yes" else "No")
}