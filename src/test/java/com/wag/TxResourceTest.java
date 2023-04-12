package com.wag;

import com.wag.model.TXCommand;
import com.wag.service.TXBulkService;
import com.wag.service.TXService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TxResourceTest {
    @Inject
    TXService txService;

    @Inject
    TXBulkService txBulkService;

    Random r = new Random();
    static String[] types = {"DEPOSIT", "WITHDRAW"};


    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/tx/hello")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }

    @Test
    public void testTXService() {
        var cmd = generateTXCommand();
        txService.processCommand(cmd);
    }

    @Test
    public void testTXBulkService() {
        List<TXCommand> batchOfCommands = IntStream.range(0, 10).mapToObj(it -> generateTXCommand()).collect(Collectors.toList());
        txBulkService.processTXCommandBatch(batchOfCommands);
    }

    private TXCommand generateTXCommand() {
        var id = "id-" + System.currentTimeMillis();
        var clientId = "client-" + r.nextInt(10);
        var type = types[r.nextInt(2)];
        return new TXCommand(id, clientId, type, 10D);
    }
}