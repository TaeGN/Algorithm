package AtCoder.ABC.ABC349.B

fun main() {
    val countArr = IntArray(26)
    readln().forEach { countArr[it - 'a']++ }
    println(if (countArr.groupBy { it }.filter { it.key > 0 }
            .all { it.value.isEmpty() || it.value.size == 2 }) "Yes" else "No")
}