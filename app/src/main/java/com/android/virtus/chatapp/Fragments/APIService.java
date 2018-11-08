package com.android.virtus.chatapp.Fragments;
import java.lang.String;

import com.android.virtus.chatapp.Notifications.MyResponse;
import com.android.virtus.chatapp.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA0L33_-0:APA91bF8q3FtnpTHly22APN0L24IV2sF6N2YQvnfNbAQzvs0IewtAGwdC5bRQB-vqfR2D6g9q5rGkh1PQtvd3axkg6G02c28HEHNYNHbiwBMMLQKR8VlG42AxFCLLjIbN4te_PeMQ_i7"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
