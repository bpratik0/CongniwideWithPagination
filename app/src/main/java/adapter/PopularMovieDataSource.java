package adapter;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import javax.inject.Inject;

import dagger.MyApplication;
import model.MoviesResponseModel;
import model.PopularMoviesModel;
import retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PopularMovieDataSource extends PageKeyedDataSource<Integer, PopularMoviesModel> {

    public static final int PAGE_SIZE = 50;
    private static final int FIRST_PAGE = 1;
    private String API_KEY = "f106692af7b706f509359e6e8f9e6096";
    @Inject
    Retrofit mRetrofit;

    @Inject
    public PopularMovieDataSource(Context context) {
        ((MyApplication) context).getNetComponent().inject(this);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, PopularMoviesModel> callback) {

        mRetrofit.create(RetrofitInterface.class)
                .getTopRatedMovies(API_KEY, FIRST_PAGE)
                .enqueue(new Callback<MoviesResponseModel>() {
                    @Override
                    public void onResponse(Call<MoviesResponseModel> call, Response<MoviesResponseModel> response) {
                        if (response.body() != null) {

                            callback.onResult(response.body().getResults(), null, FIRST_PAGE + 1);

                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponseModel> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, PopularMoviesModel> callback) {
        // do nothing
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, PopularMoviesModel> callback) {

        mRetrofit.create(RetrofitInterface.class)
                .getTopRatedMovies(API_KEY, params.key)
                .enqueue(new Callback<MoviesResponseModel>() {
                    @Override
                    public void onResponse(Call<MoviesResponseModel> call, Response<MoviesResponseModel> response) {

                        if (response.body() != null) {
                            Integer key = true ? params.key + 1 : null;
                            callback.onResult(response.body().getResults(), key);
                        }

                    }

                    @Override
                    public void onFailure(Call<MoviesResponseModel> call, Throwable t) {

                    }
                });
    }
}
