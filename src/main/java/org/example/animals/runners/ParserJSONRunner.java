package org.example.animals.runners;

import org.example.animals.entity.Animal;

import java.util.List;

import static org.example.animals.parser.AnimalParserJSON.parseAnimals;

/**
 * @author oksanapoliakova on 12.02.2024
 * @projectName Parsers
 */
public class ParserJSONRunner {
    public static void main(String[] args) {
        List<Animal> animals = parseAnimals("/Users/oksanapoliakova/Downloads/Parsers/src/main/resources/json/animals.json");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
