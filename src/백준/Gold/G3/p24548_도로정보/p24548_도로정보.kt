package 백준.Gold.G3.p24548_도로정보

const val MOD = 3

data class Section(val tCount: Int = 0, val gCount: Int = 0, val fCount: Int = 0, val pCount: Int = 0)

fun main() {
    val N = readln().toInt()
    val str = readln()
    val sectionList = mutableListOf(Section())
    val sectionMap = mutableMapOf(Section() to mutableListOf<Int>())
    repeat(N) { idx ->
        val prevSection = sectionList.getOrElse(idx) { Section() }
        val curSection = when (str[idx]) {
            'T' -> prevSection.copy(tCount = (prevSection.tCount + 1) % MOD)
            'G' -> prevSection.copy(gCount = (prevSection.gCount + 1) % MOD)
            'F' -> prevSection.copy(fCount = (prevSection.fCount + 1) % MOD)
            'P' -> prevSection.copy(pCount = (prevSection.pCount + 1) % MOD)
            else -> throw IllegalArgumentException()
        }
        sectionList.add(curSection)
        if (!sectionMap.containsKey(curSection)) sectionMap[curSection] = mutableListOf()
        sectionMap[curSection]!!.add(idx + 1)
    }

    var count = 0
    for (i in 0..N) {
        val sectionIdxList = sectionMap[sectionList[i]]!!
        val startIdx = sectionIdxList.binarySearch(i + 1).let { if (it < 0) -it - 1 else it }
        count += sectionIdxList.size - startIdx
    }

    println(count)
}