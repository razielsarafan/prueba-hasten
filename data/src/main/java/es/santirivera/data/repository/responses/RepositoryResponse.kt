package es.santirivera.data.repository.responses

interface RepositoryResponse<ResponseType> {

    val isSuccess: Boolean?
    val responseData: ResponseType?
    val errorCode: Long?
    val errorMessage: String?

}

