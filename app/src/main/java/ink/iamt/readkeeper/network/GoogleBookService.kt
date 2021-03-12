package ink.iamt.readkeeper.network

import ink.iamt.readkeeper.network.model.dto.GoogleSearchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBookService {
    @GET("books/v1/volumes")
    suspend fun search(
        @Query("q") keyword: String
    ): Response<GoogleSearchDto>
}