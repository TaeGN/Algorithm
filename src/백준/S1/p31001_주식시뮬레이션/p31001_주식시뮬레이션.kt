package 백준.S1.p31001_주식시뮬레이션

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.min

data class Company(val groupId: Int, val name: String, var stockPrice: Long)
data class Stock(val companyList: List<Company>, var cash: Long) {
    private val companyMap: Map<String, Company> = companyList.associateBy({ it.name }, { it }).toMap()
    private val stockMap: MutableMap<String, Int> = companyList.associateBy({ it.name }, { 0 }).toMutableMap()
    private fun companyByName(name: String) = companyMap[name] ?: throw IllegalArgumentException()
    fun buy(name: String, count: Int) {
        val company = companyByName(name)
        if (company.stockPrice * count > cash) return
        stockMap.compute(name) { _, v -> if (v == null) count else v + count }
        cash -= company.stockPrice * count
    }

    fun sell(name: String, count: Int) {
        val company = companyByName(name)
        val sellCount = min(count, stockMap.getOrDefault(name, 0))
        stockMap.compute(name) { _, v -> if (v == null) 0 else v - sellCount }
        cash += company.stockPrice * sellCount
    }

    fun riseByCompanyStockPrice(name: String, price: Int) {
        companyByName(name).stockPrice += price
    }

    fun riseByGroupStockPrice(groupId: Int, price: Int) {
        companyList.filter { it.groupId == groupId }
            .forEach { it.stockPrice += price }
    }

    fun riseRatioByGroupStockPrice(groupId: Int, ratio: Int) {
        companyList.filter { it.groupId == groupId }
            .forEach { it.stockPrice = (it.stockPrice * (100 + ratio) / 1000) * 10 }
    }

    fun totalAssets(): Long =
        cash + stockMap.entries.fold(0L) { acc, mutableEntry -> acc + companyByName(mutableEntry.key).stockPrice * mutableEntry.value }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val companyCount = st.nextToken().toInt()
    val cash = st.nextToken().toLong()
    val queryCount = st.nextToken().toInt()

    val companyList = (1..companyCount).map {
        st = StringTokenizer(readLine())
        Company(
            groupId = st.nextToken().toInt(),
            name = st.nextToken(),
            stockPrice = st.nextToken().toLong()
        )
    }
    val stock = Stock(companyList, cash)

    val sb = StringBuilder()
    for (i in 1..queryCount) {
        st = StringTokenizer(readLine())
        when (st.nextToken().toInt()) {
            1 -> stock.buy(
                name = st.nextToken(),
                count = st.nextToken().toInt()
            )

            2 -> stock.sell(
                name = st.nextToken(),
                count = st.nextToken().toInt()
            )

            3 -> stock.riseByCompanyStockPrice(
                name = st.nextToken(),
                price = st.nextToken().toInt()
            )

            4 -> stock.riseByGroupStockPrice(
                groupId = st.nextToken().toInt(),
                price = st.nextToken().toInt()
            )

            5 -> stock.riseRatioByGroupStockPrice(
                groupId = st.nextToken().toInt(),
                ratio = st.nextToken().toInt()
            )

            6 -> stock.cash.let(sb::appendLine)
            7 -> stock.totalAssets().let(sb::appendLine)
        }
    }

    close()
    println(sb)
}