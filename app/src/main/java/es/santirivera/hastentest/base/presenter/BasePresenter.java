package es.santirivera.hastentest.base.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import es.santirivera.domain.use_cases.base.UseCaseHandler;
import es.santirivera.domain.use_cases.providers.UseCaseProvider;
import es.santirivera.hastentest.base.activity.BaseActivity;
import es.santirivera.hastentest.base.interfaces.BaseNavigation;
import es.santirivera.hastentest.base.view.BaseView;

@SuppressWarnings("unchecked")
public abstract class BasePresenter<ListenerType extends BaseNavigation> extends Fragment {

    public interface ViewCreatedListener {
        void onViewCreated(View view);
    }

    protected final String LOG_TAG = getClass().getSimpleName();
    protected ViewCreatedListener viewCreatedListener;

    protected ListenerType callback;

    protected Context context;

    @Inject protected UseCaseHandler useCaseHandler;

    @Inject protected UseCaseProvider useCaseProvider;

    protected BaseView baseView;

    @Override
    public void onAttach(Context context) {
        try {
            callback = (ListenerType) getParentFragment();
            if (callback == null) {
                callback = (ListenerType) context;
            }
        } catch (ClassCastException e) {
            throw new RuntimeException(context + " must implement " + callback.getClass().getSimpleName());
        }

        this.context = context;
        if (context instanceof BaseActivity) {
            ((BaseActivity) this.context).component().inject((BasePresenter<BaseNavigation>) this);
        } else {
            throw new RuntimeException("baseActivity not instanceof BaseActivity");
        }

        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseView = instanceView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(baseView.getContentView(), container, false);
        setupPresenterLayout(mainView);
        ButterKnife.bind(this, mainView);
        baseView.setupLayout(mainView);
        return mainView;
    }

    private void setupPresenterLayout(View mainView) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (viewCreatedListener != null) {
            viewCreatedListener.onViewCreated(view);
        }
        doOnViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadViewData();
    }
    /**
     * @param viewCreatedListener the viewCreatedListener to set
     */
    public void setViewCreatedListener(ViewCreatedListener viewCreatedListener) {
        this.viewCreatedListener = viewCreatedListener;
    }

    protected void doOnViewCreated(View view, Bundle savedInstanceState) {

    }

    public boolean backPressed() {
        return false;
    }

    protected abstract BaseView instanceView();

    protected abstract void loadViewData();
}
