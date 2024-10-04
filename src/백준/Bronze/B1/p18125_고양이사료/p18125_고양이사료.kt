package 백준.Bronze.B1.p18125_고양이사료

fun main() {
    val (R, C) = readln().trim().split(" ").map(String::toInt)
    val matrix1 = Array(C) { readln().trim().split(" ").map(String::toInt) }
    val matrix2 = Array(R) { readln().trim().split(" ").map(String::toInt) }
    fun result(): Boolean {
        for (r in 0 until R) {
            for (c in 0 until C) {
                if (matrix2[r][c] != matrix1[C - 1 - c][r]) return false
            }
        }
        return true
    }
    if (result()) {
        println(
            """
            |>___/|        /}
            | O < |       / }
            (==0==)------/ }
            | ^  _____    |
            |_|_/     ||__|
        """.trimIndent()
        )
    } else {
        println(
            """
            |>___/|     /}
            | O O |    / }
            ( =0= )""${'"'}${'"'}  \
            | ^  ____    |
            |_|_/    ||__|
        """.trimIndent()
        )
    }
}