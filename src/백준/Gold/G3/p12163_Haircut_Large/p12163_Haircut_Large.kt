package 백준.Gold.G3.p12163_Haircut_Large

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) { tc ->
        val (B, N) = readln().trim().split(" ").map(String::toInt)
        val mArr = readln().trim().split(" ").map(String::toInt)
        fun isPossible(time: Long): Boolean {
            var count = 0L
            for (m in mArr) {
                count += time / m + 1
            }
            return count >= N
        }

        fun search(start: Long = 0, end: Long = 1_000_000_000_000_000): Long {
            val mid = start + (end - start) / 2
            if (start == mid) return if (isPossible(start)) start else end
            return if (isPossible(mid)) search(start, mid)
            else search(mid + 1, end)
        }

        fun result(): Int {
            val time = search()
            var count = mArr.fold(0L) { acc, i -> acc + (time - 1) / i + 1 }
            for ((idx, m) in mArr.withIndex()) {
                if (time % m == 0L && ++count == N.toLong()) return idx + 1
            }
            return -1
        }
        sb.appendLine("Case #${tc + 1}: ${result()}")
    }
    println(sb)
}