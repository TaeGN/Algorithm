package 백준.Platinum.P2.p11717_WallMakingGame

const val EMPTY = -1
fun main() {
    val (H, W) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(H) { readln().trim().toCharArray() }
    val dp = Array(H) { Array(W) { Array(H) { IntArray(W) { EMPTY } } } }
    fun dfs(sr: Int = 0, sc: Int = 0, er: Int = H - 1, ec: Int = W - 1): Int {
        if (sr > er || sc > ec) return 0
        if (dp[sr][sc][er][ec] != EMPTY) return dp[sr][sc][er][ec]
        var flag = 0
        for (r in sr..er) {
            for (c in sc..ec) {
                if (matrix[r][c] == '.') {
                    val curFlag = dfs(sr, sc, r - 1, c - 1) xor dfs(sr, c + 1, r - 1, ec) xor
                            dfs(r + 1, sc, er, c - 1) xor dfs(r + 1, c + 1, er, ec)
                    flag = flag or (1 shl curFlag)
                }
            }
        }
        for (i in 0 until 30) {
            if ((flag and (1 shl i)) == 0) {
                dp[sr][sc][er][ec] = i
                break
            }
        }
        return dp[sr][sc][er][ec]
    }
    println(if (dfs() != 0) "First" else "Second")
}