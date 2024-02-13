package org.example.currencies.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.example.currencies.entity.CryptoCurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author oksanapoliakova on 13.02.2024
 * @projectName Parsers
 */
public class CryptoCurrencyRateParser {
    private static final String API_URL = "https://api.coincap.io/v2/assets";

    public static void main(String[] args) {
        List<CryptoCurrency> cryptoCurrencies = fetchCryptoDataFromApi();

        // Retrives the value
        Double priceUSD = cryptoCurrencies.stream().findFirst().get().getPriceUsd();

        System.out.println(priceUSD);

        if(cryptoCurrencies != null) {
            cryptoCurrencies.forEach(System.out::println);
        } else {
            System.out.println("Failed to fetch data from the API.");
        }
    }

    private static List<CryptoCurrency> fetchCryptoDataFromApi() {
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

    private static List<CryptoCurrency> convertJsonToCryptoCurrencyList(String jsonString) {
        Gson gson = new Gson();

        // Parse the top-level JSON object
        JsonObject topLevelObject = gson.fromJson(jsonString, JsonObject.class);

        // Extract the "data" field, assuming it contains the array
        JsonArray dataArray = topLevelObject.getAsJsonArray("data");

        // Convert the array to a list of CryptoCurrency objects
        Type cryptoCurrencyListType = new TypeToken<List<CryptoCurrency>>() {}.getType();

        return gson.fromJson(dataArray, cryptoCurrencyListType);
    }
}
