package book.이펙티브코틀린.item5_예외를_활용해_코드에_제한을_걸어라


data class MyList<T>(val list: MutableList<T> = mutableListOf()) : MutableList<T> by list {
    override fun addFirst(e: T) {
        requireNotNull(e)
        check(e is String) {
            "String!!!"
        }
        list.addFirst(e)
    }

}

fun factorial(n: Int): Long {
    require(n > 0)
    return if(n <= 1) 1 else factorial(n - 1) * n
}

fun main() {
    val myList = MyList<Any?>()
    myList.addFirst(null)
    myList.addFirst("a")
    myList.addFirst(1)
//    factorial(0)
}