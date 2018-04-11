package es.santirivera.hastentest.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import es.santirivera.domain.use_cases.GetCategoriesUseCase;
import es.santirivera.domain.use_cases.base.UseCaseTaggedCallback;
import es.santirivera.hastentest.R;
import es.santirivera.hastentest.adapter.PlayersAdapter;
import es.santirivera.hastentest.base.activity.BaseActivity;


public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;

    }

    @Override
    protected void prepareInterface() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        useCaseHandler.execute(useCaseProvider.getGetCategoriesUseCase(), new Callback());

    }

    private class Callback implements UseCaseTaggedCallback<GetCategoriesUseCase.OkOutput, GetCategoriesUseCase.ErrorOutput> {

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void onSuccess(String tag, GetCategoriesUseCase.OkOutput response) {
            recyclerView.setAdapter(new PlayersAdapter(response.getCategoryModels()));
        }

        @Override
        public void onError(String tag, GetCategoriesUseCase.ErrorOutput error) {

        }

        @Override
        public void onGenericError(String tag) {

        }

        @Override
        public void onNetworkUnavailable(String tag) {

        }
    }
}
