package network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance = null;
    private static ApiService ApiService;
    //사용하고 있는 서버 BASE 주소
    private static String baseUrl = "http://13.209.19.255/9095/";

    public static Retrofit getInstance() {
        if(instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return instance;
    }

    public static ApiService getRetrofitInterface() {
        return ApiService;
    }
}
