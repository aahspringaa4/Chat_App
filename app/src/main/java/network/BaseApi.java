package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApi {
    private static Retrofit instance = null;
    private static ApiService ApiService;
    //사용하고 있는 서버 BASE 주소
    private static String baseUrl = "http://92f6-211-36-140-182.ngrok.io/";

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
