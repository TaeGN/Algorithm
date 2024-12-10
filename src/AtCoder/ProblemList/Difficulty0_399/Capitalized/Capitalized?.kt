package AtCoder.ProblemList.Difficulty0_399.Capitalized

fun main() {
    println(
        if (readln().trim()
                .let { it == (it.substring(0, 1).uppercase() + it.substring(1).lowercase()) }
        ) "Yes" else "No"
    )
}