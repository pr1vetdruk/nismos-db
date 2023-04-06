package ru.somsin.nismos.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Table {
    private static final String EXTENSION = ".nismos";
    private static final String SEPARATOR = ",";

    private final Path tablePath;

    public Table(String tableName) {
        tablePath = Paths.get(tableName + EXTENSION);
    }

    public void drop() {
        try {
            Files.delete(tablePath);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void insert(int key, String value) {
        try {
            Files.write(tablePath, concatLine(key, value).getBytes(), CREATE, APPEND);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String select(int key) {
        try {
            List<String> lines = Files.readAllLines(tablePath);
            for (int lineIndex = lines.size() - 1; lineIndex >= 0; lineIndex--) {
                String line = lines.get(lineIndex);
                if (line.startsWith(key + SEPARATOR)) {
                    return line.substring((key + "").length() + 1);
                }
            }

            return null;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private String concatLine(int key, String value) {
        return key + SEPARATOR + value + System.lineSeparator();
    }
}
