package com.beyzanur.simpracasestudy.model;

public record NotificationModel (
        String confirmationNumber,
        String message,
        String phoneNumber,
        String email,
        String firstName,
        String lastName
) {

}
