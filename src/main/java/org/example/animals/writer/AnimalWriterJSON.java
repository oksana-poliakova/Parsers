package org.example.animals.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.animals.entity.Animal;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oksanapoliakova on 12.02.2024
 * @projectName Parsers
 */
public class AnimalWriterJSON {
    public static List<Animal> readAnimalsFromJson(String filePath) {
        List<Animal> existingAnimals = new ArrayList<>();

        try (Reader reader = new FileReader(filePath)) {
            // Using TypeToken to specify the type of the generic List<Animal>
            Type type = new TypeToken<List<Animal>>() {}.getType();
            Gson gson = new Gson();
            existingAnimals = gson.fromJson(reader, type);

        } catch (FileNotFoundException e) {
            // Handle the case where the file doesn't exist
        } catch (IOException e) {
            e.printStackTrace();
        }

        return existingAnimals;
    }

    public static void writeAnimalsToJson(List<Animal> animals, String filePath) {
        List<Animal> existingAnimals = readAnimalsFromJson(filePath);
        existingAnimals.addAll(animals);

        try (Writer writer = new FileWriter(filePath)) {
            // Creating Gson with PrettyPrinting for better human-readable format
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // Converting the list to JSON format
            String json = gson.toJson(existingAnimals);
            // Writing the JSON string to the file
            writer.write(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


