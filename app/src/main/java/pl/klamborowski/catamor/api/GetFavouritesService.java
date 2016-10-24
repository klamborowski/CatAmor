package pl.klamborowski.catamor.api;

import pl.klamborowski.catamor.model.server.GetFavouritesResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Artur on 23.10.2016.
 */

public interface GetFavouritesService {

    @GET("api/images/getfavourites")
    Observable<GetFavouritesResponse> getFavourites(@Query("api_key") String apiKey,
                                                    @Query("sub_id") String subId);
}
