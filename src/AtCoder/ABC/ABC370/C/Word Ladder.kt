package AtCoder.ABC.ABC370.C

fun main() {
    val S = readln()
    val T = readln()
    val arr = mutableListOf<String>()
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    for (i in S.indices) {
        if (S[i] != T[i]) {
            if (S[i] < T[i]) list2.add(i)
            else list1.add(i)
        }
    }
    list2.reverse()
    list1.addAll(list2)
    var str = S
    for (i in list1) {
        str = str.substring(0, i) + T[i] + str.substring(i + 1)
        arr.add(str)
    }
    println(arr.size)
    println(arr.joinToString("\n"))
}