package com.angga.numgen.service;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SnowflakeIdGeneratorTest {

    @Test
    void testNextIdIsUnique() {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1, 1);

        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            long id = generator.nextId();
            assertTrue(ids.add(id), "Duplicate ID found: " + id);
        }
    }

    @Test
    void testNextIdIsOrdered() {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1, 1);

        long prevId = generator.nextId();
        for (int i = 0; i < 100; i++) {
            long id = generator.nextId();
            assertTrue(id > prevId, "ID not ordered: " + id + " <= " + prevId);
            prevId = id;
        }
    }

    @Test
    void testInvalidWorkerIdThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SnowflakeIdGenerator(0, 32); // invalid worker ID (> max 31)
        });
        assertEquals("worker Id out of range", exception.getMessage());
    }

    @Test
    void testInvalidDatacenterIdThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SnowflakeIdGenerator(32, 0); // invalid datacenter ID (> max 31)
        });
        assertEquals("datacenter Id out of range", exception.getMessage());
    }
}
