package pl.klamborowski.catamor.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.klamborowski.catamor.BuildConfig;
import pl.klamborowski.catamor.config.Config;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Artur on 23.10.2016
 */

public class ServiceFactory {
    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     *
     * @param clazz Java interface of the retrofit service
     * @return retrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> clazz) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);


        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(Config.Api.SERVICE_ENDPOINT)
                .client(client)
                .build();
        T service = retrofit.create(clazz);

        return service;
    }
}
