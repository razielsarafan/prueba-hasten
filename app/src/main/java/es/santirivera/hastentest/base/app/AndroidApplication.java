package es.santirivera.hastentest.base.app;

import android.app.Application;

import es.santirivera.hastentest.di.components.ApplicationComponent;
import es.santirivera.hastentest.di.components.DaggerApplicationComponent;
import es.santirivera.hastentest.di.modules.ApplicationModule;

public class AndroidApplication extends Application {

    private ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        initDependencies();
    }

    private void initDependencies() {
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    public ApplicationComponent component() {
        return component;
    }
}
