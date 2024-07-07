package 백준.Gold.G2.p2632_피자판매

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun IntArray.sizeMap(maxSize: Int): Map<Int, Int> {
        val totalSize = sum()
        val sizeMap = mutableMapOf(0 to 1, totalSize to 1)
        for (idx in indices) {
            var pizzaSize = totalSize
            repeat(size - 1) { count ->
                pizzaSize -= this[(idx + count) % size]
                if (pizzaSize <= maxSize) sizeMap.compute(pizzaSize) { _, v -> if (v == null) 1 else v + 1 }
            }
        }
        return sizeMap
    }

    val targetSize = readInt()
    val m = readInt()
    val n = readInt()
    val mArr = IntArray(m)
    repeat(m) { idx ->
        mArr[idx] = readInt()
    }
    val nArr = IntArray(n)
    repeat(n) { idx ->
        nArr[idx] = readInt()
    }

    val mSizeMap = mArr.sizeMap(targetSize)
    val nSizeMap = nArr.sizeMap(targetSize)
    mSizeMap.entries.sumOf { it.value * nSizeMap.getOrDefault(targetSize - it.key, 0) }.let(::println)
}