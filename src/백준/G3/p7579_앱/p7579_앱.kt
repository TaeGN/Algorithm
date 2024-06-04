package 백준.G3.p7579_앱

import java.io.StreamTokenizer
import kotlin.math.max

data class App(var byte: Int = 0, var price: Int = 0)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val appList = List(n) { App() }
    repeat(n) { idx ->
        appList[idx].byte = readInt()
    }
    repeat(n) { idx ->
        appList[idx].price = readInt()
    }

    val priceToByteMap = mutableMapOf(0 to 0)
    val newPriceToByteMap = mutableMapOf<Int, Int>()
    for (app in appList) {
        for ((price, byte) in priceToByteMap) {
            newPriceToByteMap[price + app.price] =
                max(byte + app.byte, newPriceToByteMap.getOrDefault(price + app.price, Int.MIN_VALUE))
        }
        for ((price, byte) in newPriceToByteMap) {
            priceToByteMap[price] = max(byte, priceToByteMap.getOrDefault(price, Int.MIN_VALUE))
        }
        newPriceToByteMap.clear()
    }

    priceToByteMap.asSequence().filter { it.value >= m }.map { it.key }.min().let(::println)
}