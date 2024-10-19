package AtCoder.ProblemList.Difficulty1200_1599.Appraiser

fun main() {
    val (N, M, Q) = readln().trim().split(" ").map(String::toInt)
    val result = mutableListOf<Int>()
    val A = mutableListOf<Int>()
    val B = mutableListOf<Int>()
    var start = 1
    while (start <= N) {
        var end = start + 1
        A.add(start)
        while (A.size <= (M - result.size) && B.size <= (M - result.size)) {
            println("? $start $end")
            if (readln().trim().toInt() == 0) A.add(end)
            else B.add(end)
            if (++end > N) break
        }
        if (end > N) {
            if (result.isNotEmpty()) {
                println("? ${result.first()} ${A.first()}")
                if (readln().trim().toInt() == 0) result.addAll(A)
                else result.addAll(B)
            } else {
                println("? 1 ${A.first()}")
                if (readln().trim().toInt() == 0) result.addAll(B)
                else result.addAll(A)
            }
        } else if (A.size > (M - result.size)) {
            result.addAll(B)
        } else {
            result.addAll(A)
        }
        start = end
        A.clear()
        B.clear()
    }
    println("! ${result.joinToString(" ")}")
}