package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import adapter.PopularMovieDataSource;
import adapter.PopularMovieDataSourceFactory;
import model.PopularMoviesModel;

public class MovieViewModel extends AndroidViewModel {

    public LiveData<PagedList<PopularMoviesModel>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, PopularMoviesModel>> liveDataSource;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        PopularMovieDataSourceFactory itemDataSourceFactory = new PopularMovieDataSourceFactory(application);
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(PopularMovieDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }
}
