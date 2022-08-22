package com.amazon.ata.introthreads.classroom.test.integration;

import com.amazon.ata.introthreads.classroom.BatchPasswordHasher;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BatchPasswordHasherIntegrationTest {

    private BatchPasswordHasher batchHasher;
    private List<String> passwords;
    private static final String SALT = "salt";

    @Test
    void batchPasswordHasher_implementsRunnable() {
        // GIVEN
        batchHasher = new BatchPasswordHasher(passwords, SALT);

        // WHEN
        Class batchHasherClass = batchHasher.getClass();

        // THEN
        assertTrue(Runnable.class.isAssignableFrom(batchHasherClass), "Expected BatchPasswordHatcher to implement Runnable");
    }

    @Test
    void batchPasswordHasher_overridesRunMethod() throws NoSuchMethodException {
        // GIVEN
        batchHasher = new BatchPasswordHasher(passwords, SALT);

        // WHEN
        Class batchHasherClass = batchHasher.getClass();
        Class runDeclaringClass = batchHasherClass.getMethod("run").getDeclaringClass();

        // THEN
        assertEquals(batchHasherClass, runDeclaringClass, "Expected batchPasswordHasher to override Runnable's run method.");
    }

    @Test
    void run_withPasswords_updatesPasswordtoHashes() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException  {
        // GIVEN
        passwords = Arrays.asList("password", "123");
        batchHasher = new BatchPasswordHasher(passwords, SALT);
        BatchPasswordHasher batchHasher1 = new BatchPasswordHasher(passwords, SALT);

        // WHEN
        Class batchHasherClass = batchHasher.getClass();
        Method method = batchHasherClass.getMethod("run");
        method.invoke(batchHasher);
        batchHasher1.hashPasswords();

        // THEN
        assertEquals(batchHasher1.getPasswordToHashes(), batchHasher.getPasswordToHashes(), "run method is not updating passwordsToHashes map as expected.");
    }

}
