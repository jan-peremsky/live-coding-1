package com.wag.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    String clientId;
    String type;
    Double amount;
}
