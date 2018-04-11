package es.santirivera.domain.use_cases.providers

import es.santirivera.data.repository.AppRepository
import es.santirivera.domain.use_cases.GetCategoriesUseCase

class UseCaseProvider(private val appRepository: AppRepository) {

    val getCategoriesUseCase: GetCategoriesUseCase
        get() = GetCategoriesUseCase(appRepository)
}