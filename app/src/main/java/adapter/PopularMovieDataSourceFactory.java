package adapter;


import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import model.PopularMoviesModel;

public class PopularMovieDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, PopularMoviesModel>> itemLiveDataSource = new MutableLiveData<>();
    private Context mContext;

    public PopularMovieDataSourceFactory(Context context) {
        mContext = context;
    }

    @Override
    public DataSource create() {
        PopularMovieDataSource popularMoviesDataSource = new PopularMovieDataSource(mContext);
        itemLiveDataSource.postValue(popularMoviesDataSource);
        return popularMoviesDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, PopularMoviesModel>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
