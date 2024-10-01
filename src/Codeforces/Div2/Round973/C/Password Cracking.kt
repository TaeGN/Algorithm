package Codeforces.Div2.Round973.C


fun main() {
    repeat(readln().toInt()) {
        val N = readln().toInt()
        var result = ""
        a@ while (result.length < N) {
            for (i in 0..1) {
                println("? ${result + i}")
                if (readln().toInt() == 1) {
                    result += i
                    continue@a
                }
            }
            break
        }
        while (result.length < N) {
            println("? ${"0$result"}")
            result = (if (readln().toInt() == 1) "0" else "1") + result
        }
        println("! $result")
    }
    System.out.flush()
}