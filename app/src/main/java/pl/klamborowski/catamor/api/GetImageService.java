package pl.klamborowski.catamor.api;

import pl.klamborowski.catamor.model.server.GetImageResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Artur on 23.10.2016.
 */

public interface GetImageService {

    @GET("api/images/get")
    Observable<GetImageResponse> getImage(@Query("api_key") String apiKey,
                                          @Query("format") String format,
                                          @Query("sub_id") String subId);

    enum Format {
        XML {
            @Override
            public String getFormatString() {
                return "xml";
            }
        },
        HTML {
            @Override
            public String getFormatString() {
                return "html";
            }
        },
        SRC {
            @Override
            public String getFormatString() {
                return "src";
            }
        };

        public abstract String getFormatString();
    }
}
