package es.santirivera.hastentest.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.santirivera.data.repository.AppRepository;
import es.santirivera.domain.use_cases.base.UseCaseHandler;
import es.santirivera.domain.use_cases.base.UseCaseScheduler;
import es.santirivera.domain.use_cases.base.UseCaseThreadPoolScheduler;
import es.santirivera.domain.use_cases.providers.UseCaseProvider;

@Module
public class DomainModule {

    @Singleton
    @Provides
    public UseCaseProvider provideUseCaseProvider(AppRepository appRepository) {
        return new UseCaseProvider(appRepository);
    }

    @Singleton
    @Provides
    public UseCaseHandler provideUseCaseHandler(UseCaseScheduler useCaseScheduler) {
        return new UseCaseHandler(useCaseScheduler);
    }

    @Singleton
    @Provides
    public UseCaseScheduler provideUseCaseScheduler(UseCaseThreadPoolScheduler useCaseThreadPoolScheduler) {
        return useCaseThreadPoolScheduler;
    }
}
