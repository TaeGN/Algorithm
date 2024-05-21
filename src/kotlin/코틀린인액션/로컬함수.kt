package kotlin.코틀린인액션

data class User(val id: Long, val name: String, val address: String)

fun main() {
    val user1 = User(1L, "aa", "aa@aa.com")
    saveUser(user1)
}

fun saveUser(user: User) {
    user.validateBeforeSave()
    println(user)
}
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if(value.isEmpty()) {
            throw IllegalArgumentException("$fieldName error")
        }
    }

    validate(name, "Name")
    validate(address, "address")
}
