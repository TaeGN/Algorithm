package 백준.Diamond.D5.p16880_룩비숍킹나이트궁전게임.print

import kotlin.math.min

fun R() {
    val size = 16
    val G = Array(size) { IntArray(size) }
    val bArr = BooleanArray(size * size)
    for (i in 0 until size) {
        for (j in 0 until size) {
            bArr.fill(true)
            for (k in 0 until i) {
                bArr[G[k][j]] = false
            }
            for (k in 0 until j) {
                bArr[G[i][k]] = false
            }
            if (i != 0 || j != 0) G[i][j] = bArr.indexOf(true)
        }
    }
    G.forEach { println(it.joinToString(" ") { elm -> "$elm" + " ".repeat(2 - elm.toString().length) }) }
}

fun B() {
    val size = 16
    val G = Array(size) { IntArray(size) }
    val bArr = BooleanArray(size * size)
    for (i in 0 until size) {
        for (j in 0 until size) {
            bArr.fill(true)
            for (k in 0 until min(i, j)) {
                bArr[G[k][j]] = false
            }
            if (i != 0 || j != 0) G[i][j] = bArr.indexOf(true)
        }
    }
    G.forEach { println(it.joinToString(" ") { elm -> "$elm" + " ".repeat(2 - elm.toString().length) }) }
}

fun K() {
    val size = 16
    val G = Array(size) { IntArray(size) }
    val bArr = BooleanArray(size * size)
    for (i in 0 until size) {
        for (j in 0 until size) {
            bArr.fill(true)
            if (i > 0) bArr[G[i - 1][j]] = false
            if (j > 0) bArr[G[i][j - 1]] = false
            if (i > 0 && j > 0) bArr[G[i - 1][j - 1]] = false
            G[i][j] = bArr.indexOf(true)
        }
    }
    G.forEach { println(it.joinToString(" ") { elm -> "$elm" + " ".repeat(2 - elm.toString().length) }) }
}

fun N() {
    val size = 16
    val G = Array(size) { IntArray(size) }
    val bArr = BooleanArray(size * size)
    for (i in 0 until size) {
        for (j in 0 until size) {
            bArr.fill(true)
            if (i >= 2 && j >= 1) bArr[G[i - 2][j - 1]] = false
            if (i >= 1 && j >= 2) bArr[G[i - 1][j - 2]] = false
            G[i][j] = bArr.indexOf(true)
        }
    }
    G.forEach { println(it.joinToString(" ") { elm -> "$elm" + " ".repeat(2 - elm.toString().length) }) }
}

fun P() {
    val size = 16
    val G = Array(size) { IntArray(size) }
    val bArr = BooleanArray(size * size)
    for (i in 0 until size) {
        for (j in 0 until size) {
            bArr.fill(true)
            for (k in 0 until i) {
                bArr[G[k][j]] = false
            }
            for (k in 0 until j) {
                bArr[G[i][k]] = false
            }
            if (i > 0 && j > 0) bArr[G[i - 1][j - 1]] = false
            if (i != 0 || j != 0) G[i][j] = bArr.indexOf(true)
        }
    }
    G.forEach { println(it.joinToString(" ") { elm -> "$elm" + " ".repeat(2 - elm.toString().length) }) }
}