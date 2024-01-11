package com.beyzanur.simpracasestudy.util;

import com.beyzanur.simpracasestudy.model.SaveReservationModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RequestSender {

    @Value("${reservation.booker.lambda.url}")
    private String LAMBDA_URL;

    @Value("${reservation.booker.lambda.api.key}")
    private String LAMBDA_API_KEY;

    private final RestClient restClient;

    public RequestSender() {
        restClient = RestClient.builder().build();
    }

    public String book(SaveReservationModel post) {
        return restClient.post()
                .uri(LAMBDA_URL + "/Reservation-Booker")
                .header("Authorization", LAMBDA_API_KEY)
                .body(post)
                .retrieve()
                .body(String.class);
    }

}

