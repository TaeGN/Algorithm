package 백준.S4.p14612_김식당

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

data class Order(val tableId: Int, val time: Int) : Comparable<Order> {
    override fun compareTo(other: Order): Int =
        if (time != other.time) {
            time.compareTo(other.time)
        } else {
            tableId.compareTo(other.tableId)
        }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val orders = mutableListOf<Order>()
    val sb = StringBuilder()
    fun MutableList<Order>.tableIds() =
        if (this.isEmpty()) "sleep"
        else this.fold("") { acc, order -> acc + "${order.tableId} " }
    for (i in 1..n) {
        st = StringTokenizer(readLine())
        val query = st.nextToken()
        when (query) {
            "order" -> {
                val tableId = st.nextToken().toInt()
                val time = st.nextToken().toInt()
                orders.add(Order(tableId = tableId, time = time))
            }
            "sort" -> orders.sort()
            "complete" -> {
                val tableId = st.nextToken().toInt()
                val completedOrder = orders.find { it.tableId == tableId }
                orders.remove(completedOrder)
            }
        }
        orders.tableIds().let(sb::appendLine)
    }
    close()
    println(sb)
}