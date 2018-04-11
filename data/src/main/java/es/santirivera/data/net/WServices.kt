package es.santirivera.data.net

import es.santirivera.data.dto.CategoryDTO
import retrofit2.Call
import retrofit2.http.GET

interface WServices {

    @get:GET("/bins/66851")
    val categories: Call<List<CategoryDTO>>
}