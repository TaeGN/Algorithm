package AtCoder.ProblemList.Difficulty0_399.Whichisahead

fun main() {
    val N = readln().trim().toInt()
    val P = readln().trim().split(" ").map(String::toInt)
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val (A, B) = readln().trim().split(" ").map(String::toInt)
        sb.appendLine(if (P.indexOf(A) < P.indexOf(B)) A else B)
    }
    println(sb)
}