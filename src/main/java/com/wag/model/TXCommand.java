package com.wag.model;


import lombok.Data;

@Data
public class TXCommand {
    String id;
    String clientId;
    String type;
    Double amount;
}
