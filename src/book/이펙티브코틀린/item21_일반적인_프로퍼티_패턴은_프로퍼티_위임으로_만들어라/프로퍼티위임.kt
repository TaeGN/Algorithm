package book.이펙티브코틀린.item21_일반적인_프로퍼티_패턴은_프로퍼티_위임으로_만들어라

import kotlin.reflect.KProperty

var token: String? = null
    get() {
        println("token = $field")
        return field
    }
    set(value) {
        println("oldToken = $field, newToken = $value")
        field = value
    }

var attempts: Int = 0
    get() {
        println("attempts = $field")
        return field
    }
    set(value) {
        println("oldAttempts = $field, newAttempts = $value")
        field = value
    }

private class LoggingProperty<T>(var value: T) {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        println("${prop.name} = $value")
        return value
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, newValue: T) {
        val name = prop.name.replaceFirstChar(Char::uppercaseChar)
        println("old$name = $value, new$name = $newValue")
        value = newValue
    }
}

fun main() {
    val token : String? by LoggingProperty(null)
    val attempts : Int by LoggingProperty(0)

    println(token)
    println(attempts)
}