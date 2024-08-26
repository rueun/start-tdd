package com.example.starttdd.chapter7;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CardNumberValidator {
    public CardValidity validate(String cardNumber) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://some-external-pg.com/card"))
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString(cardNumber))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return switch (response.body()) {
                case "ok" -> CardValidity.VALID;
                case "bad" -> CardValidity.INVALID;
                case "expired" -> CardValidity.EXPIRED;
                case "theft" -> CardValidity.THEFT;
                default -> CardValidity.UNKNOWN;
            };
        } catch (IOException | InterruptedException e) {
            return CardValidity.ERROR;
        }
    }
}
