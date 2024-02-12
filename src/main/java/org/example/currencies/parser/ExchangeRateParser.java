package org.example.currencies.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Currency;
import java.util.List;

/**
 * @author oksanapoliakova on 12.02.2024
 * @projectName Parsers
 */
public class ExchangeRateParser {

    // API URL for PrivatBank exchange rates
    private static final String API_URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";

    public static void main(String[] args) {
        // Fetch exchange rates from the API
        List<org.example.currencies.entity.Currency> currencies = fetchDataFromApi();

        // Print the parsed exchange rates
        if (currencies != null) {
            currencies.forEach(System.out::println);
        } else {
            System.out.println("Failed to fetch data from the API.");
        }
    }

    private static List<org.example.currencies.entity.Currency> fetchDataFromApi() {
        try {
            URL url = new URL(API_URL);
            // Open a connection to the API URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Read the JSON response from the API
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String jsonResponse = reader.readLine();
                return convertJsonToCurrencyList(jsonResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<org.example.currencies.entity.Currency> convertJsonToCurrencyList(String jsonString) {
        // Use Gson to deserialize the JSON into a list of Currency objects
        Gson gson = new Gson();
        Type currencyListType = new TypeToken<List<org.example.currencies.entity.Currency>>() {}.getType();

        return gson.fromJson(jsonString, currencyListType);
    }
}
