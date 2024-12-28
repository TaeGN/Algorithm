package AtCoder.ABC.ABC386.A

fun main() {
    val numbers = readln().trim().split(" ").map(String::toInt).sorted()
    fun result(): String {
        if (numbers[0] == numbers[1] && numbers[0] == numbers[2] && numbers[0] != numbers[3]) return "Yes"
        if (numbers[0] != numbers[1] && numbers[1] == numbers[2] && numbers[1] == numbers[3]) return "Yes"
        if (numbers[0] == numbers[1] && numbers[0] != numbers[2] && numbers[2] == numbers[3]) return "Yes"
        return "No"
    }
    println(result())
}