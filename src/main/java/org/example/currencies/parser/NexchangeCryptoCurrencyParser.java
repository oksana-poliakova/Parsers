package org.example.currencies.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.example.currencies.entity.CryptoCurrency;
import org.example.currencies.entity.NexchangeCryptoCurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author oksanapoliakova on 13.02.2024
 * @projectName Parsers
 */

public class NexchangeCryptoCurrencyParser {
    private static final String API_URL = "https://api.n.exchange/en/api/v1/currency/";

    public static void main(String[] args) {
        List<NexchangeCryptoCurrency> cryptoCurrencies = fetchCryptoDataFromApi();

        // Find and extract the NexchangeCryptoCurrency object for "bitcoin"
        NexchangeCryptoCurrency bitcoin = cryptoCurrencies.stream()
                .filter(currency -> "bitcoin".equals(currency.getName()))
                .findFirst()
                .orElse(null);

        if (bitcoin != null) {
            // Extract the name of the "bitcoin" currency
            String bitcoinName = bitcoin.getName();
            System.out.println(bitcoinName);
        } else {
            System.out.println("Bitcoin not found in the list.");
        }
        System.out.println("======");

        // Print details of all cryptoCurrencies in the list
        cryptoCurrencies.forEach(System.out::println);
    }

    private static List<NexchangeCryptoCurrency> fetchCryptoDataFromApi() {
        try {
            URL url = new URL(API_URL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String jsonResponse = reader.readLine();
                return convertJsonToCryptoCurrencyList(jsonResponse);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<NexchangeCryptoCurrency> convertJsonToCryptoCurrencyList(String jsonString) {
        Gson gson = new Gson();
        // reflexion
        Type currencyListType = new TypeToken<List<NexchangeCryptoCurrency>>() {}.getType();

        return gson.fromJson(jsonString, currencyListType);
    }
}
