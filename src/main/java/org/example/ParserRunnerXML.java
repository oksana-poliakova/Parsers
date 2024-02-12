package org.example;

import org.example.entity.Animal;

import java.util.List;

import static org.example.parser.AnimalParserXML.parseAnimals;

/**
 * @author oksanapoliakova on 12.02.2024
 * @projectName Parsers
 */
public class ParserRunnerXML {
    public static void main(String[] args) {
        List<Animal> animals = parseAnimals("/Users/oksanapoliakova/Downloads/Parsers/src/main/resources/xml/animals.xml");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
