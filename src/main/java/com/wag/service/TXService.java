package com.wag.service;

import com.wag.config.LiveCodingConfig;
import com.wag.model.Account;
import com.wag.model.TXCommand;
import com.wag.repository.TXRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TXService {
    @Inject
    TXRepository repository;

    @Inject
    LiveCodingConfig config;

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

    private Account applyTXCommand(TXCommand command, Account account) {
        //TODO for every command type, there might be a different effect on the account.amount
        account.setAmount(account.getAmount() + command.getAmount());
        return account;
    }

    private String getAccountTypeFromTXCommand(TXCommand command) {
        //TODO to be implemented
        return "account.type";
    }

    public void processCommands(List<TXCommand> commands) {
        //TODO implement parallel processing with the degree of parallelism specified by the config.parallelization()
    }
}
