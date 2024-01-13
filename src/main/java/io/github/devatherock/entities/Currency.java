package io.github.devatherock.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Currency {
    private String currencyName;
    private double amount;
}