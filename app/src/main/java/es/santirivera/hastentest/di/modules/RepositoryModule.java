package es.santirivera.hastentest.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.santirivera.data.net.NetworkManager;
import es.santirivera.data.net.WServices;
import es.santirivera.data.repository.AppRepository;
import es.santirivera.data.repository.AppRepositoryImpl;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public AppRepository provideAppRepository(
            Context context,
            WServices wServices,
            NetworkManager networkManager) {

        return new AppRepositoryImpl(context,
                wServices,
                networkManager);
    }

}
