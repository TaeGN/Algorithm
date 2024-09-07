package 백준.Platinum.P5.p9202_Boggle

const val MOD = (1L shl 61) - 1
val dr = intArrayOf(1, -1, 0, 0, 1, 1, -1, -1)
val dc = intArrayOf(0, 0, 1, -1, 1, -1, 1, -1)
fun main() {
    val hashArr = LongArray(8 * 26) { (1 until MOD).random() }
    fun hash(idx: Int, c: Char) = hashArr[idx * 26 + (c - 'A')]
    val W = readln().toInt()
    val prefixSet = mutableSetOf<Long>()
    val wordMap = mutableMapOf<Long, Int>()
    val wordList = mutableListOf<String>()
    repeat(W) {
        val S = readln()
        var hash = 0L
        for ((idx, c) in S.withIndex()) {
            hash = (hash + hash(idx, c)) % MOD
            prefixSet.add(hash)
        }
        wordMap[hash] = wordList.size
        wordList.add(S)
    }
    readln()
    val B = readln().toInt()
    val sb = StringBuilder()
    repeat(B) { idx ->
        if (idx > 0) readln()
        val matrix = Array(4) { readln().trim().toCharArray() }
        val visited = Array(4) { BooleanArray(4) }
        val selected = BooleanArray(W)
        fun dfs(r: Int, c: Int, idx: Int, hash: Long) {
            if (hash in wordMap) selected[wordMap[hash]!!] = true
            if (idx == 8) return
            visited[r][c] = true
            for (d in dr.indices) {
                val nr = r + dr[d]
                val nc = c + dc[d]
                if (nr in 0 until 4 && nc in 0 until 4 && !visited[nr][nc]) {
                    val nHash = (hash + hash(idx, matrix[nr][nc])) % MOD
                    if (nHash in prefixSet) dfs(nr, nc, idx + 1, nHash)
                }
            }
            visited[r][c] = false
        }
        for (r in 0 until 4) {
            for (c in 0 until 4) {
                val hash = hash(0, matrix[r][c])
                if (hash in prefixSet) dfs(r, c, 1, hash)
            }
        }
        var score = 0
        var maxLenWord = ""
        var wordCount = 0
        for (i in 0 until W) {
            if (!selected[i]) continue
            score += when (wordList[i].length) {
                in 3..4 -> 1
                5 -> 2
                6 -> 3
                7 -> 5
                8 -> 11
                else -> 0
            }
            if (maxLenWord.length < wordList[i].length ||
                (maxLenWord.length == wordList[i].length && maxLenWord > wordList[i])
            ) maxLenWord = wordList[i]
            wordCount++
        }
        sb.appendLine("$score $maxLenWord $wordCount")
    }
    println(sb)
}