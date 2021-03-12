package ink.iamt.readkeeper.network.model.dto

data class OpenLibrarySearchDto(
    val numFound: Int,
    val start: Int,
    val docs: List<Docs>
)

data class Docs(
    val title: String?,
    val author_name: List<String>?,
    val isbn: List<String>?,
    val publisher: List<String>?,
    val publish_date: List<String>?
)
