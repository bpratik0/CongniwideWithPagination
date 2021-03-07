package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cogniwide.cogniwidetask.databinding.MoviesListItemBinding;

import java.util.List;

import model.PopularMoviesModel;

public class CustomAdapter extends PagedListAdapter<PopularMoviesModel, CustomAdapter.UserViewHolder> {

    private Context mContext;

    public CustomAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MoviesListItemBinding productRowBinding = MoviesListItemBinding.inflate(layoutInflater, parent, false);
        return new UserViewHolder(productRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        PopularMoviesModel popularMoviesModel = getItem(position);
        holder.moviesListItemBinding.setUser(popularMoviesModel);
        holder.moviesListItemBinding.executePendingBindings();
    }

    public static DiffUtil.ItemCallback<PopularMoviesModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<PopularMoviesModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull PopularMoviesModel oldItem, @NonNull PopularMoviesModel newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }

        @Override
        public boolean areContentsTheSame(@NonNull PopularMoviesModel oldItem, @NonNull PopularMoviesModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    class UserViewHolder extends RecyclerView.ViewHolder {

        MoviesListItemBinding moviesListItemBinding;

        public UserViewHolder(@NonNull MoviesListItemBinding moviesListItemBinding) {
            super(moviesListItemBinding.getRoot());
            this.moviesListItemBinding = moviesListItemBinding;
        }
    }
}
