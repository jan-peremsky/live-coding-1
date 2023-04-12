package com.wag.service;

import com.wag.config.LiveCodingConfig;
import com.wag.model.TXCommand;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;

import java.util.List;

public class TXBulkService {
    @Inject
    LiveCodingConfig config;

    public void processTXCommandBatch(List<TXCommand> commands) {
        //TODO split the commands into sub-lists

        //TODO call processTXCommandBatchAsync for each sub-command


    }

    /**
     * This method can process the provided list of {@link TXCommand}s in an async way.
     * Made public to be better testable
     *
     * @param commands
     */
    public void processTXCommandBatchAsync(List<TXCommand> commands) {
        Log.infof("Processing commands batch asynchronously. Count %d", commands.size());
    }
}
