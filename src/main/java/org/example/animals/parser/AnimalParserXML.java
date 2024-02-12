package org.example.animals.parser;

import org.example.animals.entity.Animal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oksanapoliakova on 12.02.2024
 * @projectName Parsers
 */
public class AnimalParserXML {
    // takes a path to an XML file and returns a list of Animal objects
    public static List<Animal> parseAnimals(String filePath) {
        List<Animal> animals = new ArrayList<>();

        try {
            // Creating a DocumentBuilderFactory for document builders
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creating a document builder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Loading the XML file and creating a Document object to work with its content
            Document document = builder.parse(new File(filePath));

            // Getting the root element of the document (in this case, <animals>)
            Element animalsElement = document.getDocumentElement();
            // Getting a list of all <animal> elements inside the root element
            NodeList animalList = animalsElement.getElementsByTagName("animal");

            // Iterating through the list of <animal> elements
            for (int i = 0; i < animalList.getLength(); i++) {
                // Getting the current <animal> element
                Element animalElement = (Element) animalList.item(i);

                // Extracting animal data from the element
                String name = animalElement.getElementsByTagName("name").item(0).getTextContent();
                int age = Integer.parseInt(animalElement.getElementsByTagName("age").item(0).getTextContent());
                String breed = animalElement.getElementsByTagName("breed").item(0).getTextContent();
                double weight = Double.parseDouble(animalElement.getElementsByTagName("weight").item(0).getTextContent());
                String owner = animalElement.getElementsByTagName("owner").item(0).getTextContent();
                String description = animalElement.getElementsByTagName("description").item(0).getTextContent();

                // Creating an Animal object and adding it to the list
                Animal animal = new Animal(name, age, breed, weight, owner, description);
                animals.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Returning the list of Animal object
        return animals;
    }
}
