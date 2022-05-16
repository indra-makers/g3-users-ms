package com.co.indra.coinmarketcap.users.config;

public enum ErrorCodes {

    USER("User with this mail already exist", "001");
    String message;
    String code;

    ErrorCodes(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

}
