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
        restClient = RestClient.builder()
                .baseUrl(LAMBDA_URL)
                .build();
    }

    public SaveReservationModel book(SaveReservationModel post) {
        return restClient.post()
                .uri("/Reservation-Booker")
                .contentType(MediaType.APPLICATION_JSON)
                .header("authorization", LAMBDA_API_KEY)
                .body(post)
                .retrieve()
                .body(SaveReservationModel.class);
    }

}
