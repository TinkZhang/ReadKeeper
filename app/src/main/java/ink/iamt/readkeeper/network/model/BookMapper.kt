package ink.iamt.readkeeper.network.model

interface BookMapper <T,BOOK> {
    fun mapToBook(model: T): BOOK
    fun mapToBooks(model: List<T>): List<BOOK> =
        model.map { mapToBook(it)}

    fun mapFromBook(book: BOOK): T
}