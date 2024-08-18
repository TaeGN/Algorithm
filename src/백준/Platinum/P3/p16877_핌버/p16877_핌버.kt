package 백준.Platinum.P3.p16877_핌버

const val MAX_P = 3000000
fun main() {
    val fibonacci = mutableListOf(1, 1)
    while (fibonacci.last() < MAX_P) {
        fibonacci.add(fibonacci.last() + fibonacci[fibonacci.size - 2])
    }
    val G = IntArray(MAX_P + 1)
    val selected = BooleanArray(fibonacci.size)
    for (i in 1..MAX_P) {
        selected.fill(false)
        for (num in fibonacci) {
            if (i - num < 0) break
            selected[G[i - num]] = true
        }
        G[i] = selected.indexOf(false)
    }
    val N = readln().toInt()
    val result = readln().split(" ").map(String::toInt).fold(0) { acc, i -> acc xor G[i] }
    println(if (result != 0) "koosaga" else "cubelover")
}
