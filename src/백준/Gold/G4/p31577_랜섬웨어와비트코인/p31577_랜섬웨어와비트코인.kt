package 백준.Gold.G4.p31577_랜섬웨어와비트코인

fun main() {
    val sb = StringBuilder()
    val list = (1..20).groupBy { (it - 1) / 4 }.map { it.value.joinToString(" ") }
    sb.append("${list[0]} ${list[1]}\n".repeat(6))
    sb.append("${list[2]} ${list[3]}\n".repeat(3))
    sb.append("${list[2]} ${list[4]}\n".repeat(3))
    sb.append("${list[3]} ${list[4]}\n".repeat(3))
    println(sb)
}