package es.santirivera.domain.use_cases;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import es.santirivera.data.dto.CategoryDTO;
import es.santirivera.data.repository.AppRepository;
import es.santirivera.data.repository.responses.RepositoryResponse;
import es.santirivera.domain.model.CategoryModel;
import es.santirivera.domain.use_cases.base.StringErrorOutput;
import es.santirivera.domain.use_cases.base.UseCase;
import es.santirivera.domain.use_cases.base.UseCaseResponse;

public class GetCategoriesUseCase extends UseCase<Void, GetCategoriesUseCase.OkOutput, GetCategoriesUseCase.ErrorOutput> {

    AppRepository appRepository;

    public GetCategoriesUseCase(AppRepository appRepository) {
        this.appRepository = appRepository;
    }


    @NonNull
    @Override
    protected UseCaseResponse<GetCategoriesUseCase.OkOutput, GetCategoriesUseCase.ErrorOutput> executeUseCase(Void requestValues) {
        final RepositoryResponse<List<CategoryDTO>> response = appRepository.getCategoryList();
        if (response.isSuccess()) {
            ArrayList<CategoryModel> categoryModels = new ArrayList<>();
            for (CategoryDTO categoryDTO : response.getResponseData()) {
                categoryModels.add(new CategoryModel(categoryDTO));
            }
            return UseCaseResponse.ok(new GetCategoriesUseCase.OkOutput(categoryModels));
        } else {
            return UseCaseResponse.error(new GetCategoriesUseCase.ErrorOutput("Error retrieving categories"));
        }
    }

    public static class OkOutput {

        private final ArrayList<CategoryModel> categoryModels;

        public OkOutput(ArrayList<CategoryModel> categoryModels) {
            this.categoryModels = categoryModels;
        }

        public ArrayList<CategoryModel> getCategoryModels() {
            return categoryModels;
        }
    }

    public static class ErrorOutput extends StringErrorOutput {

        public ErrorOutput(String errorDesc) {
            super(errorDesc);
        }
    }
}
