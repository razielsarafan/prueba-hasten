package es.santirivera.hastentest.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import es.santirivera.domain.use_cases.base.UseCaseHandler;
import es.santirivera.domain.use_cases.providers.UseCaseProvider;
import es.santirivera.hastentest.base.activity.BaseActivity;
import es.santirivera.hastentest.base.app.AndroidApplication;
import es.santirivera.hastentest.di.modules.ActivityModule;
import es.santirivera.hastentest.di.modules.ApplicationModule;
import es.santirivera.hastentest.di.modules.DomainModule;
import es.santirivera.hastentest.di.modules.GsonModule;
import es.santirivera.hastentest.di.modules.RepositoryModule;
import es.santirivera.hastentest.di.modules.WebServicesModule;

@Singleton
@Component(
        modules = {
                ActivityModule.class,
                ApplicationModule.class,
                GsonModule.class,
                DomainModule.class,
                RepositoryModule.class,
                WebServicesModule.class
        })
public interface ActivityComponent {

    void inject(BaseActivity activity);

    // Exported to child components
    AndroidApplication application();

    Context context();

    UseCaseProvider useCaseProvider();

    UseCaseHandler useCaseHandler();

}
