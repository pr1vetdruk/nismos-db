package ru.somsin.nismos.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableTest {
    private Table testTable;

    @BeforeEach
    public void beforeEach() {
        testTable = new Table("test_table");
    }

    @AfterEach
    public void afterEach() {
        testTable.drop();
    }

    @Test
    public void insert_and_select_success() {
        String value = "{}";
        int key = 1;
        testTable.insert(key, value);
        String selectValue = testTable.select(key);
        assertEquals(value, selectValue);

        key = 2;
        testTable.insert(key, value);
        selectValue = testTable.select(key);
        assertEquals(value, selectValue);

        value = "{123}";
        testTable.insert(key, value);
        selectValue = testTable.select(key);
        assertEquals(value, selectValue);

        key = 3;
        testTable.insert(key, value);
        testTable.insert(key, value);
        testTable.insert(key, value);
        selectValue = testTable.select(key);
        assertEquals(value, selectValue);

        key = 2;
        selectValue = testTable.select(key);
        assertEquals(value, selectValue);
    }
}
