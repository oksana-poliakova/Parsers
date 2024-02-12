package org.example.animals.runners;

import com.google.gson.Gson;
import org.example.animals.entity.Animal;
import org.example.animals.writer.AnimalWriterJSON;

import java.util.ArrayList;
import java.util.List;

import static org.example.animals.writer.AnimalWriterJSON.writeAnimalsToJson;

/**
 * @author oksanapoliakova on 12.02.2024
 * @projectName Parsers
 */
public class AnimalWriterJSONRunner {
    public static void main(String[] args) {
        AnimalWriterJSON animalWriterJSON = new AnimalWriterJSON();

        Gson gson = new Gson();

        List<Animal> newAnimals = new ArrayList<>();
        newAnimals.add(new Animal("Lucky", 2, "Persian", 4.5, "Jane Doe", "Adorable and playful"));

        // path to JSON
        String jsonFilePath = "/Users/oksanapoliakova/Downloads/Parsers/src/main/resources/json/animals.json";

        // Write new animals to JSON
        writeAnimalsToJson(newAnimals, jsonFilePath);
    }
}
