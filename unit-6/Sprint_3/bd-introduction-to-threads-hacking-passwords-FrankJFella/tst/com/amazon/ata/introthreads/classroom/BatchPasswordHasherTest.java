package com.amazon.ata.introthreads.classroom;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BatchPasswordHasherTest {

    @Test
    void run_withPasswords_hashesPasswords() {

        // GIVEN
        List<String> passwords = Arrays.asList("password1", "password2", "password3");
        BatchPasswordHasher batchHasher = new BatchPasswordHasher(passwords, "salt");

        // WHEN
        batchHasher.run();

        // THEN
        assertTrue(batchHasher.getPasswordToHashes().keySet().containsAll(passwords), "run method did not create hashes for all passwords");
    }

}
