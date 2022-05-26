package com.co.indra.coinmarketcap.users.config;

public enum ErrorCodes {

    USER("USER WITH THIS MAIL ALREADY EXIST", "001"),
    USER_NOT_FOUND("THERE IS NOT USER BY THAT ID", "002");
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