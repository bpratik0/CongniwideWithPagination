package retrofit;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.MyApplication;
import model.MoviesResponseModel;
import model.PopularMoviesModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitRepository {

    private String API_KEY = "f106692af7b706f509359e6e8f9e6096";
    private RetrofitInterface mRetrofitService;
    Context context;
    @Inject
    Retrofit mRetrofit;

    @Inject
    public RetrofitRepository(Context context) {
        this.context = context;
    }

    public MutableLiveData<List<PopularMoviesModel>> getPopularMovies() {
        ((MyApplication) context).getNetComponent().inject(this);
        MutableLiveData<List<PopularMoviesModel>> poListMutableLiveData = new MutableLiveData<>();
        mRetrofitService = mRetrofit.create(RetrofitInterface.class);
        Call<MoviesResponseModel> call = mRetrofitService.getTopRatedMovies(API_KEY,  1);
        call.enqueue(new Callback<MoviesResponseModel>() {
            @Override
            public void onResponse(Call<MoviesResponseModel> call, Response<MoviesResponseModel> response) {
                MoviesResponseModel dataList = response.body();
                poListMutableLiveData.setValue(dataList.getResults());
            }

            @Override
            public void onFailure(Call<MoviesResponseModel> call, Throwable t) {
                poListMutableLiveData.setValue(null);
            }
        });
        return poListMutableLiveData;
    }
}
