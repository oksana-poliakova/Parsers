package org.example.currencies.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author oksanapoliakova on 13.02.2024
 * @projectName Parsers
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrency {
    private String id;
    private String symbol;
    private String name;
    private Double supply;
    private Double priceUsd;
}
