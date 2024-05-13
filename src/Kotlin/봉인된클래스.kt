package Kotlin

sealed class Expr {
    class Num(val value: Int): Expr()
    class Sum(val left: Expr, val right: Expr): Expr()
}

fun main() {
    val num1 = Expr.Num(5)
    val num2 = Expr.Num(7)
    val sum = Expr.Sum(num1, num2)
    println(eval(sum))
}

fun eval(e: Expr): Int = when(e) {
    is Expr.Num -> e.value
    is Expr.Sum -> eval(e.left) + eval(e.right)
}