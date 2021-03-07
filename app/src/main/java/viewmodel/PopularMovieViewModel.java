package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dagger.AppModule;
import dagger.MyApplication;
import dagger.NetworkModule;
import retrofit.RetrofitRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.CogniwideComponent;
import dagger.DaggerCogniwideComponent;
import dagger.PopularMoviesModule;
import model.PopularMoviesModel;

public class PopularMovieViewModel extends AndroidViewModel {

    @Inject
    public RetrofitRepository mRetrofitRepository;
    private CogniwideComponent mMyComponent;

    public PopularMovieViewModel(@NonNull Application application) {
        super(application);
        ((MyApplication) application).getNetComponent().inject(this);
    }

    public MutableLiveData<List<PopularMoviesModel>> getNewsRepository() {
        return mRetrofitRepository.getPopularMovies();
    }
}
