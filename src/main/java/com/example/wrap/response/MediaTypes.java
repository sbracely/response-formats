package com.example.wrap.response;

import org.springframework.http.MediaType;

public final class MediaTypes {

    public static final String WRAPPED_JSON_VALUE =
            "application/vnd.example.wrapped+json";

    public static final String NON_NULL_JSON_VALUE =
            "application/vnd.example.non-null+json";

    public static final MediaType WRAPPED_JSON =
            MediaType.valueOf(WRAPPED_JSON_VALUE);

    public static final MediaType NON_NULL_JSON =
            MediaType.valueOf(NON_NULL_JSON_VALUE);

    private MediaTypes() {
    }
}
