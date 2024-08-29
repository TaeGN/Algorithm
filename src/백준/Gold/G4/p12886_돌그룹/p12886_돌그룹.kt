package 백준.Gold.G4.p12886_돌그룹

data class Group(val first: Int, val second: Int, val third: Int) {
    override fun equals(other: Any?): Boolean {
        if (other is Group && first == other.first && second == other.second && third == other.third) return true
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = first
        result = 31 * result + second
        result = 31 * result + third
        return result
    }
}

fun main() {
    val (A, B, C) = readln().split(" ").map(String::toInt)
    val arr = IntArray(3)
    fun sortedGroup(a: Int, b: Int, c: Int): Group {
        arr[0] = a
        arr[1] = b
        arr[2] = c
        arr.sort()
        return Group(arr[0], arr[1], arr[2])
    }

    fun result(): Int {
        val queue = ArrayDeque<Group>()
        val set = mutableSetOf<Group>()
        fun add(group: Group) {
            if (set.add(group)) queue.add(group)
        }
        add(sortedGroup(A, B, C))
        while (queue.isNotEmpty()) {
            val (a, b, c) = queue.removeFirst()
            if (a == b && a == c) return 1
            if (a != b) add(sortedGroup(a * 2, b - a, c))
            if (a != c) add(sortedGroup(a * 2, b, c - a))
            if (b != c) add(sortedGroup(a, b * 2, c - b))
        }
        return 0
    }
    println(result())
}