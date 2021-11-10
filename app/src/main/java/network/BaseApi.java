package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApi {
    private static BaseApi instance = null;
    private static ApiService ApiService;
    //사용하고 있는 서버 BASE 주소
    private static String baseUrl = "http://13.209.19.255:9094/";

    private BaseApi() {

        //retrofit 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService = retrofit.create(ApiService.class);
    }

    public static BaseApi getInstance() {
        if (instance == null) {
            instance = new BaseApi();
        }
        return instance;
    }

    public static ApiService getRetrofitInterface() {
        return ApiService;
    }
}
