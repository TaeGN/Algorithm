package 백준.Platinum.P3.p1671_상어의저녁식사

const val EMPTY = -1

class Shark(val size: Int, val speed: Int, val intelligence: Int) {
    fun canEat(o: Shark) = size >= o.size && speed >= o.speed && intelligence >= o.intelligence
    override fun equals(other: Any?): Boolean {
        if (other is Shark) return size == other.size && speed == other.speed && intelligence == other.intelligence
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = size
        result = 31 * result + speed
        result = 31 * result + intelligence
        return result
    }
}

fun main() {
    val N = readln().toInt()
    val list = mutableListOf<Shark>()
    repeat(N) { list.add(readln().split(" ").map(String::toInt).let { Shark(it[0], it[1], it[2]) }) }
    val outLists = List(N) { mutableListOf<Int>() }
    for ((aIdx, aShark) in list.withIndex()) {
        for ((bIdx, bShark) in list.withIndex()) {
            if (aShark == bShark && aIdx <= bIdx) continue
            if (aShark.canEat(bShark)) outLists[aIdx].add(bIdx)
        }
    }

    val idArr = IntArray(N) { EMPTY }
    val visited = BooleanArray(N)
    fun dfs(aId: Int): Boolean {
        for (bId in outLists[aId]) {
            if (visited[bId] || (list[aId] == list[bId] && aId <= bId)) continue
            visited[bId] = true
            if (idArr[bId] == EMPTY || dfs(idArr[bId])) {
                idArr[bId] = aId
                return true
            }
        }
        return false
    }

    var result = N
    for (aId in 0 until N) {
        repeat(2) {
            if (dfs(aId)) {
                visited.fill(false)
                result--
            }
        }
    }
    println(result)
}