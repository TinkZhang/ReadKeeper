package ink.iamt.readkeeper.network

import ink.iamt.readkeeper.network.model.dto.OpenLibrarySearchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryService {
    @GET("search.json")
    suspend fun search(
        @Query("q") keyword: String,
        @Query("page") page: Int
    ): Response<OpenLibrarySearchDto>
}