package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.MealTo;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MealDao implements Database<MealTo> {
    private static final File DATA_FILE = new File("d://dev/database.txt");

    @Override
    public synchronized void add(MealTo elem) throws IOException {
        try (FileWriter writer = new FileWriter(DATA_FILE, true)) {
            writer.write(String.format("%d %s %s %d %b", elem.getId(), elem.getDateTime().toString(),
                    elem.getDescription(), elem.getCalories(), elem.isExcess()) + '\n');
        }
    }

    @Override
    public synchronized MealTo get(int id) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (Integer.parseInt(currentLine.split(" ")[0]) == id) {
                    String[] parts = currentLine.split(" ");
                    StringBuilder description = new StringBuilder();
                    for (int i = 2; i < parts.length - 2; i++) {
                        description.append(parts[i]).append(" ");
                    }
                    return new MealTo(Integer.parseInt(parts[0]), LocalDateTime.parse(parts[1]),
                            description.toString().trim(), Integer.parseInt(parts[parts.length - 2]),
                            Boolean.parseBoolean(parts[parts.length - 1]));
                }
            }
        }
        throw new ArrayIndexOutOfBoundsException("no data by id " + id);
    }

    @Override
    public List<MealTo> getAll() throws IOException {
        List<MealTo> allMeals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(" ");
                StringBuilder description = new StringBuilder();
                for (int i = 2; i < parts.length - 2; i++) {
                    description.append(parts[i]).append(" ");
                }
                allMeals.add(new MealTo(Integer.parseInt(parts[0]), LocalDateTime.parse(parts[1]),
                        description.toString().trim(), Integer.parseInt(parts[parts.length - 2]),
                        Boolean.parseBoolean(parts[parts.length - 1])));
            }
        }
        return allMeals;
    }

    @Override
    public synchronized void delete(int id) throws IOException {
        File newFile = new File(DATA_FILE.getParentFile(), "/temp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (Integer.parseInt(currentLine.split(" ")[0]) == id) continue;
                writer.write(currentLine + '\n');
            }
        }
        DATA_FILE.delete();
        newFile.renameTo(DATA_FILE);
    }

    @Override
    public synchronized void update(MealTo elem) throws IOException {
        File newFile = new File(DATA_FILE.getParentFile(), "/temp.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
             BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (Integer.parseInt(currentLine.split(" ")[0]) == elem.getId()) {
                    writer.write(String.format("%d %s %s %d %b", elem.getId(), elem.getDateTime().toString(),
                            elem.getDescription(), elem.getCalories(), elem.isExcess()) + '\n');
                } else {
                    writer.write(currentLine + '\n');
                }
            }
        }
        DATA_FILE.delete();
        newFile.renameTo(DATA_FILE);
    }
}
