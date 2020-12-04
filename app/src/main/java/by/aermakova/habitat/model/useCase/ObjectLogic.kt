package by.aermakova.habitat.model.useCase

open class ObjectLogic {
    fun getStringCount(all: Int, nominative: String?, genitive: String?, accusative: String?): String? {
        val end = all % 10
        var c: String? = "$all "
        c += when (end) {
            1 -> {
                nominative
            }
            in 2..4 -> {
                accusative
            }
            else -> genitive
        }
        return c
    }
}