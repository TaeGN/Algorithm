package book.코틀린인액션

class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet()): MutableCollection<T> by innerSet {
    var addCount = 0
    override fun add(element: T): Boolean {
        addCount++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        addCount += elements.size
        return innerSet.addAll(elements)
    }
}

fun main() {
    val set = CountingSet<Int>()
    set.add(1)
    println(set.addCount)
    set.addAll(arrayOf(1,2,3,4,5))
    println(set.addCount)
}