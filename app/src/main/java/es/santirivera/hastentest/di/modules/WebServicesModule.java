package es.santirivera.hastentest.di.modules;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.santirivera.data.net.NetworkManager;
import es.santirivera.data.net.NetworkManagerImpl;
import es.santirivera.data.net.WServices;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebServicesModule {

    @Provides
    @Singleton
    public WServices provideWServices(Retrofit retrofit) {
        return retrofit.create(WServices.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.myjson.com")
                .build();
    }

    @Singleton
    @Provides
    NetworkManager providesNetworkManager(Context context) {
        return new NetworkManagerImpl(context);
    }
}
