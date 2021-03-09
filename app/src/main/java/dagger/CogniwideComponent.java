package dagger;

import com.cogniwide.cogniwidetask.PopularMoviesActivity;

import javax.inject.Singleton;

import adapter.PopularMovieDataSource;
import retrofit.RetrofitRepository;
import viewmodel.PopularMovieViewModel;

@Singleton
@Component(modules = {PopularMoviesModule.class, AppModule.class, NetworkModule.class})
public interface CogniwideComponent {
    void inject(PopularMovieViewModel popularMovieViewModel);

    void inject(RetrofitRepository popularMoviesActivity);

    void inject(PopularMoviesActivity popularMoviesActivity);

    void inject(PopularMovieDataSource itemDataSource);
}
