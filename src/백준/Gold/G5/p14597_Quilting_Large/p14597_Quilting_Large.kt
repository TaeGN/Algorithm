package 백준.Gold.G5.p14597_Quilting_Large

import java.io.StreamTokenizer
import kotlin.math.abs
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val H = nextInt()
    val W = nextInt()
    val image = List(H) { IntArray(W) }
    fun List<IntArray>.getOrMaxValue(r: Int, c: Int): Int =
        if (r in 0 until H && c in 0 until W) this[r][c] else Int.MAX_VALUE

    repeat(H) { r ->
        repeat(W) { c ->
            image[r][c] = nextInt()
        }
    }

    repeat(H) { r ->
        repeat(W) { c ->
            image[r][c] = abs(image[r][c] - nextInt()).let { it * it }
            if (r > 0) image[r][c] += min(
                image.getOrMaxValue(r - 1, c - 1),
                min(image.getOrMaxValue(r - 1, c), image.getOrMaxValue(r - 1, c + 1))
            )
        }
    }

    println(image[H - 1].min())
}