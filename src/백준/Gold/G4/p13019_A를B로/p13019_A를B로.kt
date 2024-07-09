package 백준.Gold.G4.p13019_A를B로

fun main() {
    fun String.changeCount(other: String): Int {
        if (this == other) return 0
        val countArr = IntArray(26)
        this.forEach { countArr[it - 'A']++ }
        other.forEach { countArr[it - 'A']-- }
        if (countArr.any { it != 0 }) return -1

        var totalCount = 0
        var j = length - 1
        for(i in (length - 1) downTo 0) {
            if(this[i] == other[j]) j--
            else totalCount++
        }
        return totalCount
    }

    println(readln().changeCount(readln()))
}