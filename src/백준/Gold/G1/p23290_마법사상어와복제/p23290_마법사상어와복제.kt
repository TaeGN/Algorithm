package 백준.Gold.G1.p23290_마법사상어와복제

import java.io.StreamTokenizer

const val MAP_SIZE = 4

enum class Direction(val dr: Int, val dc: Int) {
    W(0, -1),
    NW(-1, -1),
    N(-1, 0),
    NE(-1, 1),
    E(0, 1),
    SE(1, 1),
    S(1, 0),
    SW(1, -1);

    fun rotateCCW45(): Direction =
        Direction.entries[(this.ordinal + Direction.entries.size - 1) % Direction.entries.size]

    fun rotateCCW90(): Direction =
        Direction.entries[(this.ordinal + Direction.entries.size - 2) % Direction.entries.size]
}

data class Fish(var r: Int, var c: Int, var d: Direction, var isAlive: Boolean = true) {
    fun die() {
        isAlive = false
    }
}

class Shark(var r: Int, var c: Int)
data class Grid(private var fishSmellEndTime: Int = -1, private val fishList: MutableList<Fish> = mutableListOf()) {
    fun addFish(fish: Fish) {
        fishList.add(fish)
    }

    fun fishCount(): Int = fishList.size
    fun hasNotFishSmell(time: Int) = time > fishSmellEndTime
    fun except(time: Int) {
        if (fishList.isEmpty()) return
        fishList.forEach(Fish::die)
        fishSmellEndTime = time + 2
        reset()
    }

    fun reset() {
        fishList.clear()
    }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val m = readInt()
    val s = readInt()
    var fishList = mutableListOf<Fish>()
    val map = List(MAP_SIZE + 1) { List(MAP_SIZE + 1) { Grid() } }
    fun List<List<Grid>>.clear() = this.forEach { it.forEach(Grid::reset) }

    repeat(m) {
        val r = readInt()
        val c = readInt()
        val d = Direction.entries[readInt() - 1]
        val fish = Fish(r, c, d)
        fishList.add(fish)
        map[r][c].addFish(fish)
    }
    val shark = Shark(readInt(), readInt())
    fun isValidByMap(r: Int, c: Int) = r >= 1 && c >= 1 && r <= MAP_SIZE && c <= MAP_SIZE
    fun isValidByFishSmell(r: Int, c: Int, time: Int) = map[r][c].hasNotFishSmell(time)
    fun isValidByShark(r: Int, c: Int) = r != shark.r || c != shark.c
    fun isValid(r: Int, c: Int, time: Int) =
        isValidByMap(r, c) && isValidByShark(r, c) && isValidByFishSmell(r, c, time)

    repeat(s) { time ->
        val clonedFishList = mutableListOf<Fish>()
        map.clear()
        for (fish in fishList) {
//            1. 물고기 복제
            clonedFishList.add(fish.copy())

//            2. 물고기 이동
            for (i in 0 until Direction.entries.size) {
                val nextR = fish.r + fish.d.dr
                val nextC = fish.c + fish.d.dc
                if (isValid(nextR, nextC, time)) {
                    fish.r = nextR
                    fish.c = nextC
                    break
                }
//                2-1. 이동할 수 있는 방향으로 회전
                fish.d = fish.d.rotateCCW45()
            }
            map[fish.r][fish.c].addFish(fish)
        }

        //            3. 상어 3칸 이동
        var maxCount = -1
        val maxCountSelected = Array(3) { intArrayOf(0, 0) }
        fun moveShark(count: Int = 0, r: Int, c: Int, selected: Array<IntArray>) {
            if (count == 3) {
                var totalFishCount = 0
                for ((idx, arr) in selected.withIndex()) {
                    if(selected.asSequence().take(idx).any { arr[0] == it[0] && arr[1] == it[1] }) continue
                    totalFishCount += map[arr[0]][arr[1]].fishCount()
                }

                if (maxCount < totalFishCount) {
                    maxCount = totalFishCount
                    for ((idx, arr) in selected.withIndex()) {
                        maxCountSelected[idx][0] = arr[0]
                        maxCountSelected[idx][1] = arr[1]
                    }
                }
                return
            }

            var curD = Direction.N
            for (i in 0 until 4) {
                val nextR = r + curD.dr
                val nextC = c + curD.dc
                if (isValidByMap(nextR, nextC)) {
                    selected[count][0] = nextR
                    selected[count][1] = nextC
                    moveShark(count + 1, nextR, nextC, selected)
                }
                curD = curD.rotateCCW90()
            }

        }
        moveShark(0, shark.r, shark.c, Array(3) { intArrayOf(0, 0) })
        maxCountSelected.forEach { map[it[0]][it[1]].except(time) }
        shark.r = maxCountSelected.last()[0]
        shark.c = maxCountSelected.last()[1]
//            4. 두 번 전 연습에서 생긴 물고기 냄새 사라짐
//            5. 복제 마법 완료
        fishList = fishList.filter(Fish::isAlive).toMutableList()
        fishList.addAll(clonedFishList)

    }
    println(fishList.size)
}