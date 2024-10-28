package AtCoder.ProblemList.Difficulty800_1199.BadJuice

fun main() {
    val N = readln().trim().toInt()
    val arr = Array(4 * N) { IntArray(2) }
    fun search(start: Int = 1, end: Int = N, idx: Int = 1) {
        arr[idx][0] = start
        arr[idx][1] = end
        if (start != end) {
            val mid = (start + end) / 2
            search(start, mid, idx * 2)
            search(mid + 1, end, idx * 2 + 1)
        }
    }
    search()
    var count = 0
    val map = mutableMapOf<Int, Int>()
    var mapIdx = 0
    var log = 0
    var result = List(arr.size.toString(2).length + 1) { StringBuilder() }
    for (i in 2 until arr.size step 2) {
        if (arr[i][0] == 0) continue
        val (l, r) = arr[i]
        if (i.toString(2).length > log) {
            if (count > 0) {
                result[log].insert(0, count)
                mapIdx++
            }
            log = i.toString(2).length
            count = 0
        }
        count += (r - l + 1)
        map[i] = mapIdx
        result[log].append(" ${(l..r).joinToString(" ")}")
    }
    if (count > 0) result[log].insert(0, count)
    result = result.filter { it.isNotEmpty() }
    println(result.size)
    println(result.joinToString("\n"))
    val S = readln().trim()
    var idx = 2
    while (true) {
        if (S[map[idx]!!] == '0') idx++
        if (arr[idx][0] == arr[idx][1]) break
        else idx *= 2
    }
    println(arr[idx][0])
}

