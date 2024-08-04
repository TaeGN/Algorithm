package 백준.Platinum.P4.p14939_불끄기

import kotlin.math.min

class BulbMatrix(private val flags: IntArray = IntArray(SIZE)) {
    companion object {
        const val SIZE = 10
        const val IMPOSSIBLE = Int.MAX_VALUE shr 4
        val dr = listOf(0, 1, 0, -1)
        val dc = listOf(1, 0, -1, -1)
    }

    fun set(r: Int, c: Int, isOn: Boolean) {
        if (isOn) flags[r] = flags[r] or (1 shl c)
    }

    fun minCount(): Int {
        fun sequence(r: Int = 0, c: Int = 0): Int {
            if (r == SIZE) return 0
            if (isOn(r, c)) {
                if (r == SIZE - 1) return IMPOSSIBLE
                switch(r + 1, c)
                val count = sequence(r + (c + 1) / SIZE, (c + 1) % SIZE)
                switch(r + 1, c)
                return 1 + count
            }
            return sequence(r + (c + 1) / SIZE, (c + 1) % SIZE)
        }

        fun minCount(idx: Int = 0, count: Int = 0): Int {
            if (idx == SIZE) return count + sequence()
            var minCount = minCount(idx + 1, count)
            switch(0, idx)
            minCount = min(minCount, minCount(idx + 1, count + 1))
            switch(0, idx)
            return minCount
        }

        val minCount = minCount()
        return if (minCount >= IMPOSSIBLE) -1 else minCount
    }

    private fun isOn(r: Int, c: Int) = flags[r] and (1 shl c) != 0
    private fun switch(r: Int, c: Int) {
        flags[r] = flags[r] xor (1 shl c)
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (nr in 0 until SIZE && nc in 0 until SIZE) {
                flags[nr] = flags[nr] xor (1 shl nc)
            }
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bulbMatrix = BulbMatrix()
    repeat(BulbMatrix.SIZE) { r ->
        val input = readLine()
        repeat(BulbMatrix.SIZE) { c ->
            bulbMatrix.set(r, c, input[c] == 'O')
        }
    }
    println(bulbMatrix.minCount())
}