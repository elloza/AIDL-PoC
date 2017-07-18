package com.lozasolutions.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lozasolutions.data.model.BankResultResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Loza on 17/07/2017.
 */

public interface BankRemoteService {

    String ENDPOINT = "http://api.fixer.io/";

    @GET("/latest")
    Observable<BankResultResponse> getLatest(@Query("symbols") String symbols);


    /******** Helper class that sets up a new services *******/
    class Creator {

        public static BankRemoteService newBankService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BankRemoteService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(BankRemoteService.class);
        }
    }
}