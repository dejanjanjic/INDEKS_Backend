package net.etfbl.indeks.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PushNotificationService {

    private static final String EXPO_PUSH_URL = "https://exp.host/--/api/v2/push/send";

    private final OkHttpClient client = new OkHttpClient();

    public void sendPushNotification(String pushToken, String title, String body) throws IOException {
        // Ensure the pushToken starts with "ExponentPushToken"
        if (!pushToken.startsWith("ExponentPushToken")) {
            throw new IllegalArgumentException("Invalid Expo Push Token");
        }

        // Create the JSON payload
        String jsonPayload = String.format(
                "{ \"to\": \"%s\", \"title\": \"%s\", \"body\": \"%s\", \"sound\": \"default\" }",
                pushToken, title, body
        );

        // Build the request
        RequestBody bodyContent = RequestBody.create(jsonPayload, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(EXPO_PUSH_URL)
                .post(bodyContent)
                .build();

        // Send the request and handle the response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }
            System.out.println("Notification sent successfully: " + response.body().string());
        }
    }
}
