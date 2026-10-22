package com.sanchez.bankingmysql.api.dto;

import com.sanchez.bankingmysql.domain.BankAccount;
import com.sanchez.common.dto.BaseResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;


public class AccountLookupResponse extends BaseResponse {

    private List<BankAccount> accounts;

    public AccountLookupResponse() {
        super();
    }

    public AccountLookupResponse(String message, List<BankAccount> accounts) {
        super(message);
        this.accounts = accounts;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountLookupResponse that = (AccountLookupResponse) o;
        return Objects.equals(accounts, that.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accounts);
    }

    @Override
    public String toString() {
        return "AccountLookupResponse(message=" + getMessage() + ", accounts=" + accounts + ")";
    }

    public static Builder<?, ?> builder() {
        return new AccountLookupResponseBuilder();
    }

    public abstract static class Builder<T extends AccountLookupResponse, B extends Builder<T, B>>
            extends BaseResponse.Builder<T, B> {

        protected Builder(T response) {
            super(response);
        }

        public B accounts(List<BankAccount> accounts) {
            response.setAccounts(accounts);
            return self();
        }
    }

    private static final class AccountLookupResponseBuilder
            extends Builder<AccountLookupResponse, AccountLookupResponseBuilder> {

        private AccountLookupResponseBuilder() {
            super(new AccountLookupResponse());
        }

        @Override
        protected AccountLookupResponseBuilder self() {
            return this;
        }
    }


}
