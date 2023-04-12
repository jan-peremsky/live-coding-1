package com.wag.service;

import com.wag.config.LiveCodingConfig;
import com.wag.model.Account;
import com.wag.model.TXCommand;
import com.wag.repository.TXRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TXService {
    @Inject
    TXRepository repository;

    @Inject
    LiveCodingConfig config;

    /**
     * Main service method to process one {@link TXCommand}.
     * For each command there is an associated account we want to change according to the commands type and it's amount and/or additional attributes.
     *
     * @param command
     */
    public void processCommand(TXCommand command) {
        Log.infof("Processing command: %s", command);
        //store the command
        repository.storeTXCommand(command);

        //find the appropriate account based on the clientID and the type of the TXCommand
        String accountType = getAccountTypeFromTXCommand(command);
        Account account = repository.getAccount(command.getClientId(), accountType);

        //decide how to manipulate the account.amount
        account = applyTXCommand(command, account);

        //store the account and store also the command
        repository.storeAccount(account);
    }

    /**
     * We have a command and the corresponding account. So let's apply the command to the account. I.e. add or subtract the amount in most cases.
     *
     * @param command
     * @param account
     * @return
     */
    private Account applyTXCommand(TXCommand command, Account account) {
        //TODO for every command type, there might be a different effect on the account.amount
        account.setAmount(account.getAmount() + command.getAmount());
        return account;
    }

    /**
     * For the given {@link TXCommand#getType()} returns the associated {@link Account} type. For the given TXCommand we'll find the corresponding
     * Account instance and apply the command to the account.
     *
     * @param command
     * @return
     */
    private String getAccountTypeFromTXCommand(TXCommand command) {
        //TODO to be implemented
        return "account.type";
    }

}
