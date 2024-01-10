package com.beyzanur.simpracasestudy.model;

import lombok.Builder;

@Builder
public record LoginResponse (
    String accessToken

) {

}
