package ru.javawebinar.topjava.model;

import java.io.*;
import java.time.LocalDateTime;

public class MealTo {
    private int id;
    private static int counter;
    private LocalDateTime dateTime;
    private String description;
    private int calories;
    private boolean excess;
    private static final String ID_FILE_PATH = "D:\\dev\\Java\\Projects\\topjava\\src\\main\\resources\\id.txt";

    static {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ID_FILE_PATH));
            counter = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess) throws IOException {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        synchronized (MealTo.class) {
            this.id = counter++;
            try(FileWriter writer = new FileWriter(ID_FILE_PATH)) {
                writer.write(counter + "");
            }
        }
    }

     public MealTo(int id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public MealTo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isExcess() {
        return excess;
    }

    public void setExcess(boolean excess) {
        this.excess = excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
