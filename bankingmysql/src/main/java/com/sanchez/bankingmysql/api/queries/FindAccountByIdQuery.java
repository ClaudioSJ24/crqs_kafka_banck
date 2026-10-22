package com.sanchez.bankingmysql.api.queries;

import com.sanchez.cqrs.core.querys.BaseQuery;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FindAccountByIdQuery extends BaseQuery {

    private String id;

    public FindAccountByIdQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .toString();
    }
}
