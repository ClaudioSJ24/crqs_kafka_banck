package com.sanchez.common.dto;

import java.util.Objects;

public class BaseResponse {

    private String message;

    public BaseResponse() {
    }

    public BaseResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseResponse that = (BaseResponse) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "BaseResponse(message=" + message + ")";
    }

    public static Builder<?, ?> builder() {
        return new BaseResponseBuilder();
    }

    public abstract static class Builder<T extends BaseResponse, B extends Builder<T, B>> {

        protected final T response;

        protected Builder(T response) {
            this.response = response;
        }

        protected abstract B self();

        public B message(String message) {
            response.setMessage(message);
            return self();
        }

        public T build() {
            return response;
        }
    }

    private static final class BaseResponseBuilder extends Builder<BaseResponse, BaseResponseBuilder> {

        private BaseResponseBuilder() {
            super(new BaseResponse());
        }

        @Override
        protected BaseResponseBuilder self() {
            return this;
        }
    }


}
