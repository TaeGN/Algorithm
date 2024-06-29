package 백준.Gold.G4.p2661_좋은수열

fun main() {
    fun List<Array<String>>.addAll(idx: Int, num: Int) {
        for (i in 1..idx) {
            this[i][idx] = this[i][idx - 1] + num
        }
    }

    fun minMatchNumber(size: Int): String {
        val subStringMatrix = List(size + 1) { Array(size + 1) { "" } }
        var result = ""
        fun String.next(): Boolean {
            if (length == size) {
                result = this
                return true
            }
            val newLength = length + 1

            var flag = 0
            for (num in 1..3) {
                var isValid = true
                for (i in 1..(newLength / 2)) {
                    if (subStringMatrix[newLength - 2 * i + 1][newLength - i] == subStringMatrix[newLength - i + 1][newLength - 1] + num) {
                        isValid = false
                        break
                    }
                }
                if (isValid) flag = flag or (1 shl num)
            }

            for (num in 1..3) {
                if (flag and (1 shl num) == 0) continue
                subStringMatrix.addAll(newLength, num)
                if ((this + num).next()) return true
            }

            return false
        }

        result.next()
        return result
    }

    val n = readln().toInt()
    println(minMatchNumber(n))
}