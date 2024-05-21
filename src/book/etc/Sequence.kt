package book.etc

val s1 = sequenceOf(1,2,3)
val s2 = (1..100).asSequence()
val s3 = generateSequence(1) { it + 1 }.take(5)

fun main() {
    println("${s1.sum()} ${s2.sum()} ${s3.sum()}")
}