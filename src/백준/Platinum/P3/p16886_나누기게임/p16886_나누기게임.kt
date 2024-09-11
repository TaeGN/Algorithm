package 백준.Platinum.P3.p16886_나누기게임

fun main() {
    val N = readln().toInt()
    val list = mutableListOf(0)
    var sum = 0
    for (i in 1..N) {
        sum += i
        if (sum > N) break
        list.add(sum)
    }
    val G = IntArray(N + 1)
    val sumG = IntArray(N + 1)
    val selected = BooleanArray(20)
    for (i in 1..N) {
        selected.fill(false)
        for (idx in 2 until list.size) {
            val value = list[idx]
            if (i < value) break
            if ((i - value) % idx == 0) {
                val start = 1 + (i - value) / idx
                val end = start + idx - 1
                selected[sumG[end] xor sumG[start - 1]] = true
            }
        }
        G[i] = selected.indexOf(false)
        sumG[i] = sumG[i - 1]
        if (G[i] != 0) sumG[i] = sumG[i] xor G[i]
    }
    fun result(): Int {
        if (G[N] != 0) {
            for (idx in 2 until list.size) {
                val value = list[idx]
                if ((N - value) % idx == 0) {
                    val start = 1 + (N - value) / idx
                    val end = start + idx - 1
                    if ((sumG[end] xor sumG[start - 1]) == 0) return idx
                }
            }
        }
        return -1
    }
    println(result())
}