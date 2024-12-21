package AtCoder.ABC.ABC385.B

fun main() {
    val (H, W, X, Y) = readln().trim().split(" ").map(String::toInt)
    val matrix = List(H) { readln().trim().toCharArray() }
    val count = List(H) { IntArray(W) }
    var curX = X - 1
    var curY = Y - 1
    count[curX][curY]++
    readln().trim().forEach {
        var nextX = curX
        var nextY = curY
        when (it) {
            'U' -> nextX = curX - 1
            'D' -> nextX = curX + 1
            'L' -> nextY = curY - 1
            'R' -> nextY = curY + 1
        }
        if (nextX in 0 until H && nextY in 0 until W && matrix[nextX][nextY] != '#') {
            curX = nextX
            curY = nextY
        }
        count[curX][curY]++
    }
    var result = 0
    for (x in 0 until H) {
        for (y in 0 until W) {
            if (matrix[x][y] == '@' && count[x][y] > 0) result++
        }
    }
    println("${curX + 1} ${curY + 1} $result")
}