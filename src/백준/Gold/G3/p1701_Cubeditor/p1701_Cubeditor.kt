package 백준.Gold.G3.p1701_Cubeditor

import kotlin.math.max

fun main() {
    fun String.kmpFailList(): IntArray {
        val failArr = IntArray(this.length)
        var j = 0
        for (i in 1 until this.length) {
            while (j > 0 && this[i] != this[j]) j = failArr[j - 1]
            if (this[i] == this[j]) failArr[i] = ++j
        }
        return failArr
    }

    fun String.cubelover(): Int {
        var maxSubstringLength = 0
        for (i in indices) {
            maxSubstringLength = max(maxSubstringLength, this.substring(i).kmpFailList().max())
        }

        return maxSubstringLength
    }


    val str = System.`in`.bufferedReader().readLine()
    println(str.cubelover())
}