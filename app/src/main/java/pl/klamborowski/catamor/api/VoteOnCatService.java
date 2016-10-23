package pl.klamborowski.catamor.api;

import pl.klamborowski.catamor.model.server.VoteOnCatResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Artur on 23.10.2016.
 */

public interface VoteOnCatService {

    @GET("api/images/vote")
    Observable<VoteOnCatResponse> vote(@Query("api_key") String apiKey,
                                       @Query("sub_id") String subId,
                                       @Query("image_id") String imageId,
                                       @Query("score") int score);
}
