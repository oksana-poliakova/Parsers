package org.example.currencies.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author oksanapoliakova on 12.02.2024
 * @projectName Parsers
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    private String ccy;
    private String baseCcy;
    private Double buy;
    private Double sale;
}
