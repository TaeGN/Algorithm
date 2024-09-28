package 백준.Silver.S5.p2891_카약과강풍

fun main() {
    val (N, S, R) = readln().trim().split(" ").map(String::toInt)
    val arr = IntArray(N + 1) { 1 }
    readln().trim().split(" ").map(String::toInt).forEach { arr[it]-- }
    readln().trim().split(" ").map(String::toInt).forEach { arr[it]++ }
    var result = 0
    for (i in 1..N) {
        if (arr[i] >= 1) arr[i]--
        else {
            if (i > 1 && arr[i - 1] > 0) arr[i - 1]--
            else if (i < N && arr[i + 1] > 1) arr[i + 1]--
            else result++
        }
    }
    println(result)
}