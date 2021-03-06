package dagger;

import android.content.Context;

import javax.inject.Singleton;

import adapter.PopularMovieDataSource;
import retrofit.RetrofitRepository;

@Module
public class PopularMoviesModule {

    private final Context context;

    public PopularMoviesModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public RetrofitRepository provideContext() {
        return new RetrofitRepository(context);
    }

    @Singleton
    @Provides
    public PopularMovieDataSource provideItemDataSource() {
        return new PopularMovieDataSource(context);
    }
}
