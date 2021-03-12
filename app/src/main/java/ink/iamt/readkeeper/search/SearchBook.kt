package ink.iamt.readkeeper.search

data class SearchBook(
    val title: String = "",
    val author: String = "",
    val publishTime: String = "",
    val publisher: String = "",
    val imageUrl: String = "",
    val price: String = "",
    val rating: Float = 0f,
    val isbn: String = ""

)
