package book.이펙티브코틀린.item11_가독성을_목표로_설계하라

import java.io.*

data class Person(val name: String)
var person: Person? = null

fun main() {
//    가변 프로퍼티 안전 호출 let 사용 -> 스마트 캐스트 가능
    person = Person("aa")
    person?.let(::println)

    val br = InputStreamReader(System.`in`)
        .let(::BufferedReader)
}