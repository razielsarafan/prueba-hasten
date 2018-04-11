package es.santirivera.data.utils

object Preconditions {

    fun <T> checkNotNull(reference: T?): T {
        if (reference == null) {
            throw NullPointerException()
        }
        return reference
    }
}
