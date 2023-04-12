package com.wag.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TXCommand {
    String id;
    String clientId;
    String type;
    Double amount;
}
