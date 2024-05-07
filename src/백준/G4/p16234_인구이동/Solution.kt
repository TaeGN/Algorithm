package 백준.G4.p16234_인구이동

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs

val dr = intArrayOf(0,1,0,-1)
val dc = intArrayOf(1,0,-1,0)
lateinit var unionPopulationMap: MutableMap<Int, Int>
lateinit var unionIds: Array<IntArray>
lateinit var visited: Array<BooleanArray>
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val mapSize = st.nextToken().toInt()
    val min = st.nextToken().toInt()
    val max = st.nextToken().toInt()

    unionIds = Array(mapSize) { IntArray(mapSize) { 0 } }
    unionPopulationMap = mutableMapOf()
    for(r in 0 until mapSize) {
        st = StringTokenizer(readLine())
        for(c in 0 until mapSize) {
            unionPopulationMap[getHash(r, c)] = st.nextToken().toInt()
            unionIds[r][c] = getHash(r, c)
        }
    }

    visited = Array(mapSize) { BooleanArray(mapSize) { false } }
    var count = -1;
    do {
//        visited 배열 초기화
        for(rowArray in visited) {
            rowArray.fill(false)
        }
        count++
    } while (bfs(min, max))

    println(count)
}

private fun bfs(min: Int, max: Int): Boolean {
    val queue = ArrayDeque<Int>()
    var canMove = false
    for(r in unionIds.indices) {
        for(c in unionIds.indices) {
            if(visited[r][c]) continue
            visited[r][c] = true
            queue.add(getHash(r, c))
            val population = unionPopulationMap[unionIds[r][c]]!!
            val curUnionId = unionIds[r][c]
            var totalPopulation = 0
            var totalCount = 0

            while(!queue.isEmpty()) {
                val curHash = queue.removeFirst()
                val curR = getR(curHash)
                val curC = getC(curHash)
                if(population != unionPopulationMap[unionIds[curR][curC]]!!) canMove = true

                for(d in dr.indices) {
                    val nextR = curR + dr[d]
                    val nextC = curC + dc[d]
                    if(isNotValid(nextR, nextC) || visited[nextR][nextC]) continue
                    val diff = abs(unionPopulationMap[unionIds[curR][curC]]!! - unionPopulationMap[unionIds[nextR][nextC]]!!)
                    if(diff in min..max) {
                        queue.add(getHash(nextR, nextC))
                        visited[nextR][nextC] = true
                    }
                }

                totalPopulation += unionPopulationMap[unionIds[curR][curC]]!!
                totalCount++
                unionIds[curR][curC] = curUnionId
            }

            unionPopulationMap[curUnionId] = totalPopulation / totalCount
        }
    }

    return canMove
}

private fun getHash(r: Int, c: Int): Int {
    return r * unionIds.size + c
}

private fun getR(hash: Int): Int {
    return hash / unionIds.size
}

private fun getC(hash: Int): Int {
    return hash % unionIds.size
}

private fun isNotValid(r:Int, c: Int): Boolean {
    return !isValid(r, c)
}
private fun isValid(r: Int, c: Int): Boolean {
    return r >= 0 && c >= 0 && r < unionIds.size && c < unionIds.size
}
