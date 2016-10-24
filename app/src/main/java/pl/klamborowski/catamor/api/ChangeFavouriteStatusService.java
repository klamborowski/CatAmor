package pl.klamborowski.catamor.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Artur on 23.10.2016.
 */

public interface ChangeFavouriteStatusService {

    @GET("api/images/favourite")
    Observable<Void> vote(@Query("api_key") String apiKey,
                          @Query("sub_id") String subId,
                          @Query("image_id") String imageId,
                          @Query("action") Action action);

    enum Action {
        ADD,
        REMOVE
    }
}
