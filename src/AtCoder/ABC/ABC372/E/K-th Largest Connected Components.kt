package AtCoder.ABC.ABC372.E

fun main() {
    val (N, Q) = readln().split(" ").map(String::toInt)
    val parents = IntArray(N + 1) { it }
    val groupArr = Array(N + 1) { intArrayOf(it) }
    fun find(id: Int): Int = if (parents[id] == id) id else find(parents[id]).also { parents[id] = it }
    fun union(id1: Int, id2: Int) {
        if (find(id1) == find(id2)) return
        val arr1 = groupArr[find(id1)]
        val arr2 = groupArr[find(id2)]
        val nArr = IntArray(10) { -1 }
        var idx = 0
        var i = 0
        var j = 0
        while (idx < nArr.size && (i < arr1.size || j < arr2.size)) {
            if (i == arr1.size) nArr[idx++] = arr2[j++]
            else if (j == arr2.size) nArr[idx++] = arr1[i++]
            else if (arr1[i] > arr2[j]) nArr[idx++] = arr1[i++]
            else nArr[idx++] = arr2[j++]
        }
        groupArr[find(id1)] = nArr
        parents[find(id2)] = find(id1)
    }

    val sb = StringBuilder()
    repeat(Q) {
        val (type, A, B) = readln().split(" ").map(String::toInt)
        if (type == 1) union(A, B)
        else sb.appendLine(groupArr[find(A)].getOrElse(B - 1) { -1 })
    }
    println(sb)
}