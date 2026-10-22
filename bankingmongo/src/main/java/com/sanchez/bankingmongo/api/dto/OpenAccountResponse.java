package com.sanchez.bankingmongo.api.dto;

import com.sanchez.common.dto.BaseResponse;

public class OpenAccountResponse extends BaseResponse {

    private String id;

    public OpenAccountResponse() {
    }

    public OpenAccountResponse(String id) {
        this.id = id;
    }

    public OpenAccountResponse(String message, String id) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
