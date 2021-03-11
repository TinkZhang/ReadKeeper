package ink.iamt.readkeeper.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val openLibraryApi: OpenLibraryService by lazy {
        Retrofit.Builder()
            .baseUrl("http://openlibrary.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenLibraryService::class.java)
    }

    val googleBookApi: GoogleBookService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleBookService::class.java)
    }
}