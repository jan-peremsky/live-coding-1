package com.wag.resource;

import com.wag.model.TXCommand;
import com.wag.service.TXBulkService;
import com.wag.service.TXService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/tx")
public class TxResource {
    @Inject
    TXService service;

    @Inject
    TXBulkService bulkService;

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @Path("oneCommand")
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitCommand(TXCommand command) {
        try {
            Log.infof("Received command: %s", command);
            service.processCommand(command);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

    @POST
    @Path("manyCommands")
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitCommandBatch(List<TXCommand> commands) {
        try {
            Log.infof("Received %d commands", commands.size());
            bulkService.processTXCommandBatch(commands);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        }
    }

}
