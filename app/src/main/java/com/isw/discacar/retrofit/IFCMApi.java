package com.isw.discacar.retrofit;

import com.isw.discacar.models.FCMBody;
import com.isw.discacar.models.FCMResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMApi {

    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAJGqcrRs:APA91bEioCCfoGEktKjeyxpFeMTkHDhLaBh8vybNjZo-Bmnf-J8iGR8ExAzkrersLZLvXnncpYwM1YJT41yYJmC87Z3U4yWTWQNgseqOHxJtEHyoosgcPJ18JYyIem9MRSSt4EwMkpcU"
    })

    @POST("fcm/send")
    Call<FCMResponse> send(@Body FCMBody body);

}
