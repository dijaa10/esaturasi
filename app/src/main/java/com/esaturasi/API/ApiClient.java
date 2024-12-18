package com.esaturasi.API;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL= "http://10.0.2.2/esaturasi_web/";
    private static Retrofit retrofit = null;

    public static Retrofit getApi(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}

