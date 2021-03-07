package adapter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import model.PopularMoviesModel;

public class ItemViewModel extends AndroidViewModel {

    public LiveData<PagedList<PopularMoviesModel>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, PopularMoviesModel>> liveDataSource;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(application);
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }
}
