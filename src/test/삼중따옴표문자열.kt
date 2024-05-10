package test

fun main() {
    val msg = """
        abc
        def
        ghi
        """.trimIndent()

    val msg2 = """
        #abc
        #dfg
        #ghi
    """.trimMargin("#")

    println(msg)
    println(msg2)
}