package 백준.Silver.S1.p26650_그램팬

fun main() {
    val regex = Regex((0..25).joinToString("") { ('A' + it) + "+" })
    val pattern = regex.toPattern()
    val matcher = pattern.matcher(readln())
    var count = 0L
    while (matcher.find()) {
        val str = matcher.group()
        val aCount = str.lastIndexOf('A') + 1
        val zCount = str.length - str.indexOf('Z')
        count += aCount.toLong() * zCount
    }
    println(count)
}