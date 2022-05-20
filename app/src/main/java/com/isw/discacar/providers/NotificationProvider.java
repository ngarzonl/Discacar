package com.isw.discacar.providers;

import com.isw.discacar.models.FCMBody;
import com.isw.discacar.models.FCMResponse;
import com.isw.discacar.retrofit.IFCMApi;
import com.isw.discacar.retrofit.RetrofitClient;

import retrofit2.Call;

public class NotificationProvider {

    private String url = "https://fcm.googleapis.com";

    public NotificationProvider() {
    }

    public Call<FCMResponse> sendNotification(FCMBody body) {
        return RetrofitClient.getClientObject(url).create(IFCMApi.class).send(body);
    }
}
