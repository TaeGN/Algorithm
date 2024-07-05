package 백준.Gold.G2.p19238_스타트택시

import java.io.StreamTokenizer

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
const val EMPTY = 0
const val WALL = 1
val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val M = readInt()
    val startFuel = readInt()
    val map = IntArray(N * N)
    repeat(N * N) { idx ->
        map[idx] = readInt()
    }

    val distanceMatrix = List(N * N) { IntArray(N * N) { IMPOSSIBLE } }
    val queue = ArrayDeque<Int>()
    val visited = BooleanArray(N * N)
    for (r in 0 until N) {
        for (c in 0 until N) {
            val startHash = r * N + c
            if (map[startHash] == WALL) continue
            queue.clear()
            visited.fill(false)
            queue.add(startHash)
            visited[startHash] = true
            var count = 0
            while (queue.isNotEmpty()) {
                repeat(queue.size) {
                    val curHash = queue.removeFirst()
                    distanceMatrix[startHash][curHash] = count
                    val curR = curHash / N
                    val curC = curHash % N
                    for (d in dr.indices) {
                        val nextR = curR + dr[d]
                        val nextC = curC + dc[d]
                        if (nextR in 0 until N && nextC in 0 until N) {
                            val nextHash = nextR * N + nextC
                            if (visited[nextHash] || map[nextHash] == WALL) continue
                            queue.add(nextHash)
                            visited[nextHash] = true
                        }
                    }
                }
                count++
            }
        }
    }

    val startHash = (readInt() - 1) * N + (readInt() - 1)
    val infoList = List(M) { IntArray(4) }
    repeat(M) { idx ->
        for (i in infoList[idx].indices) {
            infoList[idx][i] = readInt() - 1
        }
    }

    val visitedArr = BooleanArray(M)
    var count = 0
    var curHash = startHash
    var curFuel = startFuel
    while (++count <= M) {
        var minDistance = IMPOSSIBLE
        var infoIdx = -1
        for ((idx, info) in infoList.withIndex()) {
            if (visitedArr[idx]) continue
            val nextHash = info[0] * N + info[1]
            val distance = distanceMatrix[curHash][nextHash]
            if (minDistance < distance) continue
            if (minDistance == distance && infoIdx != -1 && infoList[infoIdx][0] * N + infoList[infoIdx][1] < nextHash) continue
            minDistance = distance
            infoIdx = idx
        }



        if (infoIdx >= 0) {
            visitedArr[infoIdx] = true
            val minInfo = infoList[infoIdx]
            val distance1 = distanceMatrix[curHash][minInfo[0] * N + minInfo[1]]
            val distance2 = distanceMatrix[minInfo[0] * N + minInfo[1]][minInfo[2] * N + minInfo[3]]
            if (curFuel >= distance1 + distance2) {
                curHash = minInfo[2] * N + minInfo[3]
                curFuel += distance2 - distance1
                continue
            }
        }

        curFuel = -1
        break
    }

    println(curFuel)
}