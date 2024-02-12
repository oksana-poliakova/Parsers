package org.example.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.entity.Animal;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oksanapoliakova on 12.02.2024
 * @projectName Parsers
 */
public class AnimalParserJSON {
    // Method for parsing animals from a JSON file
    public static List<Animal> parseAnimals(String filePath) {
        List<Animal> animals = new ArrayList<>();

        try (Reader reader = new FileReader(filePath)) {
            // Creating a Gson instance for JSON processing
            Gson gson = new Gson();
            // Parsing the JSON file into a JsonObject
            JsonObject json = gson.fromJson(reader, JsonObject.class);

            // Getting the JsonArray of animals from the JsonObject
            JsonArray animalArray = json.getAsJsonArray("animals");

            // Iterating through the array of animal elements
            for (JsonElement element : animalArray) {
                // Getting the JsonObject representing a single animal
                JsonObject animalObject = element.getAsJsonObject();

                // Extracting animal data from the JsonObject
                String name = animalObject.get("name").getAsString();
                int age = animalObject.get("age").getAsInt();
                String breed = animalObject.get("breed").getAsString();
                double weight = animalObject.get("weight").getAsDouble();
                String owner = animalObject.get("owner").getAsString();
                String description = animalObject.get("description").getAsString();

                // Creating an Animal object and adding it to the list
                Animal animal = new Animal(name, age, breed, weight, owner, description);
                animals.add(animal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animals; // Returning the list of Animal objects
    }
}
