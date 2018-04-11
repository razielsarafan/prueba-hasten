package es.santirivera.data.repository;


import java.util.List;

import es.santirivera.data.dto.CategoryDTO;
import es.santirivera.data.repository.responses.RepositoryResponse;

public interface AppRepository {

    String getTag();

    RepositoryResponse<List<CategoryDTO>> getCategoryList();

}

