package org.example;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String csvFile = "students.csv";
        int id = 121212;
        String name = "jack";
        String lastname = "A";
        int age = 23;
        Student student = new Student(id, name, lastname, age);

        String[] data = {String.valueOf(student.getId()), student.getName(), student.getLastname(), String.valueOf(student.getAge())};

        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.append(String.join(", ", data)).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile)).build()) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                System.out.println("ID: " + nextLine[0]);
                System.out.println("NAME: " + nextLine[1]);
                System.out.println("LASTNAME: " + nextLine[2]);
                System.out.println("AGE: " + nextLine[3]);
                System.out.println("---------------");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}