package AtCoder.ABC.ABC384.C

fun main() {
    val scoreArr = readln().trim().split(" ").map(String::toInt)
    val list = mutableListOf<String>()
    for (i in 0 until 5) {
        val newList = mutableListOf(('A' + i).toString())
        for (str in list) {
            newList.add(str + ('A' + i))
        }
        list.addAll(newList)
    }
    list.sortWith(compareBy({ -it.sumOf { c -> scoreArr.getOrNull(c - 'A') ?: 0 } }, { it }))
    println(list.joinToString("\n"))
}