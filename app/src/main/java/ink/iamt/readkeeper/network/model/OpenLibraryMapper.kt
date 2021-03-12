package ink.iamt.readkeeper.network.model

import ink.iamt.readkeeper.network.model.dto.Docs
import ink.iamt.readkeeper.search.SearchBook

class OpenLibraryMapper: BookMapper<Docs, SearchBook> {
    override fun mapToBook(model: Docs): SearchBook {
        return SearchBook(
            title = model.title ?: "",
            author = model.author_name?.reduce { acc, s -> "$acc,\n$s" } ?: "",
            publishTime = model.publish_date?.first() ?: "",
            publisher = model.publisher?.reduce { acc, s -> "$acc,\n$s"} ?: "",
            isbn = model.isbn?.first { it.length == 13 } ?: ""
        )
    }

    override fun mapFromBook(book: SearchBook): Docs {
        TODO("Not yet implemented")
    }

}