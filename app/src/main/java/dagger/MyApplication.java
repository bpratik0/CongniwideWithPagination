package dagger;

import android.app.Application;

import javax.inject.Inject;

public class MyApplication extends Application {
    private CogniwideComponent mCogniwideComponent;

    @Inject
    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCogniwideComponent = DaggerCogniwideComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("http://api.themoviedb.org/3/movie/")).
                        popularMoviesModule(new PopularMoviesModule(this)).build();
    }

    public CogniwideComponent getNetComponent() {
        return mCogniwideComponent;
    }
}
