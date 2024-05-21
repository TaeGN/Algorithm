package book.코틀린인액션

data class Person(val name: String, val age: Int) {
    fun info() = "${name}님의 나이는 ${age}입니다."
}

fun main() {
    val people = listOf(Person("aa", 3), Person("bb", 9))
    val infoList1 = people.map { it.info() }
    val infoList2 = people.map(Person::info)
    val infoList3 = people.map(Person::name)
    println(infoList1)
    println(infoList2)
    println(infoList3)
}