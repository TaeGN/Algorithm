package 백준.Gold.G2.p3499_미토콘드리아이브

enum class Sex { M, F }
data class Individual(
    val id: Int,
    var father: Individual? = null,
    var mother: Individual? = null,
    var sex: Sex = Sex.M,
    var alive: Boolean = true,
) {
    var dna: Int = -id
        get() = if (field > 0) field else mother?.dna ?: field
        set(value) {
            field = value
            mother?.dna = value
        }
}

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val individualList = MutableList(n + 1) { Individual(it) }.apply { this[0].alive = false }
    repeat(n) { idx ->
        individualList[idx + 1].sex = Sex.valueOf(readLine())
    }

    val m = readLine().toInt()
    repeat(m) {
        val input = readLine().split(" ")
        if (input.size == 1) {
            val id = -input[0].toInt()
            individualList[id].alive = false
        } else {
            val (father, mother, sex) = input.let {
                Triple(
                    individualList[it[0].toInt()],
                    individualList[it[1].toInt()],
                    Sex.valueOf(it[2])
                )
            }
            val id = individualList.size
            val individual = Individual(id, father, mother, sex)
            individualList.add(individual)
        }
    }

    val k = readLine().toInt()
    repeat(k) {
        val (id, dna) = readLine().split(" ").map(String::toInt).let { it[0] to it[1] }
        individualList[id].dna = dna
    }

    fun result(): String {
        val group = individualList.filter(Individual::alive).groupingBy(Individual::dna).eachCount()
        if (group.size == 1) return "YES"
        if (group.filter { it.key > 0 }.size > 1) return "NO"
        return "POSSIBLY"
    }

    println(result())
}