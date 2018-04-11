package es.santirivera.hastentest.fragment;

import es.santirivera.domain.use_cases.GetCategoriesUseCase;
import es.santirivera.domain.use_cases.base.UseCaseTaggedCallback;
import es.santirivera.hastentest.base.activity.BaseActivity;
import es.santirivera.hastentest.base.presenter.BasePresenter;
import es.santirivera.hastentest.base.view.BaseView;

public class PlayerListFragment extends BasePresenter<PlayerListListener> implements PlayerListListener {

    private PlayerListView view;

    @Override
    protected BaseView instanceView() {
        view = new PlayerListView((BaseActivity) getActivity(), this);
        return view;
    }

    @Override
    protected void loadViewData() {
        useCaseHandler.execute(useCaseProvider.getGetCategoriesUseCase(), new Callback());
    }


    private class Callback implements UseCaseTaggedCallback<GetCategoriesUseCase.OkOutput, GetCategoriesUseCase.ErrorOutput> {

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void onSuccess(String tag, GetCategoriesUseCase.OkOutput response) {
            view.onCategoryModelsReceived(response.getCategoryModels());
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
