package com.sanchez.bankingmongo.api.command;

import com.sanchez.cqrs.core.commands.BaseCommand;

public class CloseAccountCommand extends BaseCommand {

    private CloseAccountCommand() {
        super();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseCommand.Builder<CloseAccountCommand, Builder> {

        private Builder() {
            super(new CloseAccountCommand());
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
