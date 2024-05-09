package test


infix fun Any.to(other: Any) = Pair(this, other)
infix fun Int.plus(other: Int) = this + other

fun main() {
    val (number, name) = 1 to "one"
    println("$name = $number")
    println(1 plus 2)
    println(1 plus 2 plus 3 plus 4)
}