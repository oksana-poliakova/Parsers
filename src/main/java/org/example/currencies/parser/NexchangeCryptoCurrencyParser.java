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
        getCryptoCurrencies();
    }

    /**
     * Fetches a list of NexchangeCryptoCurrency objects from the API,
     * finds and extracts the NexchangeCryptoCurrency object for "bitcoin",
     * and prints details of all crypto currencies in the list.
     */
    private static void getCryptoCurrencies() {
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

    /**
     * Fetches JSON data from the Nexchange API and converts it into a list of NexchangeCryptoCurrency objects.
     * @return List of NexchangeCryptoCurrency objects
     */
    private static List<NexchangeCryptoCurrency> fetchCryptoDataFromApi() {
        try {
            URL url = new URL(API_URL);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Read the JSON response from the API and convert it into a list of NexchangeCryptoCurrency objects
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String jsonResponse = reader.readLine();
                return convertJsonToCryptoCurrencyList(jsonResponse);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a JSON string into a list of NexchangeCryptoCurrency objects using Gson.
     * @param jsonString JSON string from the API response
     * @return List of NexchangeCryptoCurrency objects
     */
    private static List<NexchangeCryptoCurrency> convertJsonToCryptoCurrencyList(String jsonString) {
        Gson gson = new Gson();
        // Use reflection to define the type of the list of NexchangeCryptoCurrency objects
        Type currencyListType = new TypeToken<List<NexchangeCryptoCurrency>>() {}.getType();
        // Convert the JSON string into a list of NexchangeCryptoCurrency objects
        return gson.fromJson(jsonString, currencyListType);
    }
}
