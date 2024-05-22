package book.이펙티브코틀린.item12_연산자_오버로드를_할_때는_의미에_맞게_사용하라

fun Int.factorial(): Int = (1..this).product()
fun Iterable<Int>.product(): Int = fold(1) { acc, i -> acc * i }

fun main() {
    println(5.factorial())
}