package adapter;


import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import model.PopularMoviesModel;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, PopularMoviesModel>> itemLiveDataSource = new MutableLiveData<>();
    private Context mContext;

    public ItemDataSourceFactory(Context context) {
        mContext = context;
    }

    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource(mContext);
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, PopularMoviesModel>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
