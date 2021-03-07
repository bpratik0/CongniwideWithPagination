package retrofit;

import model.MoviesResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("popular")
    Call<MoviesResponseModel> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);
}
