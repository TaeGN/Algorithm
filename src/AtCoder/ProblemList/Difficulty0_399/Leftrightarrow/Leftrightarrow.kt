package AtCoder.ProblemList.Difficulty0_399.Leftrightarrow


fun main() {
    val S = readln().trim()
    println(if (S == "<${"=".repeat(S.length - 2)}>") "Yes" else "No")
}