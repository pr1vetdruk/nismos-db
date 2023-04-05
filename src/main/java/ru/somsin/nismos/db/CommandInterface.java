package ru.somsin.nismos.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CommandInterface {
    private final String table;

    public CommandInterface(String tableName) {
        this.table = tableName + ".nismos";
    }

    public void insert(int key, String value) {
        try {
            Path path = Paths.get(table);
            Files.write(path, (key + "," + value + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String select(int key) {
        try {
            Path path = Paths.get(table);
            List<String> lines = Files.readAllLines(path);
            for (int lineIndex = lines.size() - 1; lineIndex >= 0; lineIndex--) {
                String line = lines.get(lineIndex);
                if (line.startsWith(key + ",")) {
                    return line.substring((key + "").length() + 1);
                }
            }

            return null;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
