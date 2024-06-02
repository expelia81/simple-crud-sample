package com.example.samplecrud.etc.r2dbc_orm.exception.common;

import lombok.Builder;

public class CommonExceptions {

    public static class DataNotFoundException extends RuntimeException {
        @Builder
        public DataNotFoundException(String message) {
            super(message);
        }
    }

}
