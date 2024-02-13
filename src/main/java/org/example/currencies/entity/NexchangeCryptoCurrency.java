package org.example.currencies.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author oksanapoliakova on 13.02.2024
 * @projectName Parsers
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NexchangeCryptoCurrency {
    @SerializedName("code")
    private String code;
    @SerializedName("name")
    private String name;
    @SerializedName("is_crypto")
    private boolean isCrypto;
    @SerializedName("minimal_amount")
    private String minimalAmount;


}
