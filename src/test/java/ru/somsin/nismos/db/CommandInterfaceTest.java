package ru.somsin.nismos.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandInterfaceTest {
    private CommandInterface commandInterface;

    @BeforeEach
    public void beforeEach() {
        Path path = Paths.get("test_table.nismos");
        try {
            Files.delete(path);
        } catch (IOException ignored) {
        }

        commandInterface = new CommandInterface("test_table");
    }

    @Test
    public void insert_and_select_success() {
        String value = "{}";
        int key = 1;

        commandInterface.insert(key, value);
        String selectValue = commandInterface.select(key);
        assertEquals(value, selectValue);

        key = 2;

        commandInterface.insert(key, value);
        selectValue = commandInterface.select(key);
        assertEquals(value, selectValue);

        value = "{123}";

        commandInterface.insert(key, value);
        selectValue = commandInterface.select(key);

        assertEquals(value, selectValue);
    }
}
