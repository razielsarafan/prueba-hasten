package es.santirivera.hastentest.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import es.santirivera.domain.use_cases.base.UseCaseHandler;
import es.santirivera.domain.use_cases.providers.UseCaseProvider;
import es.santirivera.hastentest.base.app.AndroidApplication;
import es.santirivera.hastentest.di.components.ActivityComponent;
import es.santirivera.hastentest.di.components.DaggerActivityComponent;
import es.santirivera.hastentest.di.modules.ApplicationModule;

public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent component;

    @Inject
    protected UseCaseProvider useCaseProvider;
    @Inject
    protected UseCaseHandler useCaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnCreate();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    protected abstract int getContentView();

    private AndroidApplication getBaseApplication() {
        return ((AndroidApplication) getApplication());
    }

    protected void setOnCreate() {
        if (getContentView() > 0) {
            setContentView(getContentView());
            ButterKnife.bind(this);
            component().inject(this);
            prepareInterface();
        }
    }


    public ActivityComponent component() {
        if (component == null) {
            component = DaggerActivityComponent
                    .builder().applicationModule(new ApplicationModule(getBaseApplication())).build();
        }
        return component;
    }


    protected abstract void prepareInterface();


}
