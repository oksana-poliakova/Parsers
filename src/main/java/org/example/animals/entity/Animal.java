package org.example.animals.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author oksanapoliakova on 12.02.2024
 * @projectName Parsers
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    private String name;
    private int age;
    private String breed;
    private double weight;
    private String owner;
    private String description;
}
