package com.wag.repository;

import com.wag.model.Account;
import com.wag.model.TXCommand;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class TXRepository {

    private ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    /**
     * Always find or create one
     *
     * @param clientId
     * @param type
     * @return
     */
    public Account getAccount(String clientId, String type) {
        Account account = accounts.computeIfAbsent(getAccountKey(clientId, type), key -> new Account(clientId, type, 0D));
        Log.infof("Found account: %s", account);
        return account;
    }

    /**
     * Store modified account
     *
     * @param account
     * @return
     */
    public Account storeAccount(Account account) {
        account = accounts.put(getAccountKey(account.getClientId(), account.getType()), account);
        Log.infof("Stored account: %s", account);
        return account;
    }

    /**
     * Stores TXCommand to DB
     *
     * @param command
     * @return
     */
    public TXCommand storeTXCommand(TXCommand command) {
        Log.infof("Stored command: %s", command);
        return command;
    }

    private static String getAccountKey(String clientId, String type) {
        return Objects.requireNonNull(clientId) + "_" + Objects.requireNonNull(type);
    }


}
