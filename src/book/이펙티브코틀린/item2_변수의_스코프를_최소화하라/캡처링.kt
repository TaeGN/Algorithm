package book.이펙티브코틀린.item2_변수의_스코프를_최소화하라


fun prime(): List<Int> {
    var numbers = (2..100).toList()
    val primes = mutableListOf<Int>()
    while (numbers.isNotEmpty()) {
        val prime = numbers.first()
        primes += prime
        numbers = numbers.filter { it % prime != 0 }
    }

    return primes.toList()
}

fun prime(n: Int) = sequence {
    var numbers = generateSequence(2) { it + 1 }
    while (true) {
        val prime = numbers.first()
        yield(prime)
        numbers = numbers.drop(1).filter { it % prime != 0 }
    }
}.takeWhile { it <= n }.toList()

fun main() {
    println(prime())
    println(prime(100))
}