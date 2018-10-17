package ru.dmitriyt.testproject.data.source.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.dmitriyt.testproject.BuildConfig;

public class APIClient {
    private static String BASE_URL = "http://95.213.236.54:3000/api/v1/";
    private static APIClient instance;

    public static APIClient getInstance() {
        return instance != null ? instance : new APIClient();
    }

    private APIInterface apiInterface;

    private APIClient () {
        apiInterface = initRetrofit(initOkHttpClient()).create(APIInterface.class);
    }

    public APIInterface getApiInterface() {
        return apiInterface;
    }

    private OkHttpClient initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    private Retrofit initRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
