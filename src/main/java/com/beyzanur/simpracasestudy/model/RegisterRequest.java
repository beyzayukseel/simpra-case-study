package com.beyzanur.simpracasestudy.model;

public record RegisterRequest (
        String firstname,
        String lastname,
        String email,
        String password
) {
}
