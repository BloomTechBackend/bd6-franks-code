package com.amazon.ata.introthreads.classroom;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class to pre-compute hashes for all common passwords to speed up cracking the hacked database.
 *
 * Passwords are downloaded from https://github.com/danielmiessler/SecLists/tree/master/Passwords/Common-Credentials
 */
public class PasswordHasher {
    // should create the file in your workspace directory
    private static final String PASSWORDS_AND_HASHES_FILE = "./passwordsAndHashesOutput.csv";

    // a "salt" is a value used to encrypt/hash data to make it harder to decrypt or de-hash
    //               usually a salt is a long string of random values (64, 28, 256, 512, 1024 chars)
    //               "BookFace" used a short constant as a salt - NOT GOOD SECURITY!
    private static final String DISCOVERED_SALT = "salt";  // the word "salt" is the salt for example

    /**
     * Generates hashes for all of the given passwords.
     *
     * @param passwords List of passwords to hash
     * @return map of password to hash
     * @throws InterruptedException
     */
    public static Map<String, String> generateAllHashes(List<String> passwords) throws InterruptedException {
        // newConcurrentMap class is a thread-safe version of a Map
        // Hold the result we are returning
        Map<String, String> passwordToHashes = Maps.newConcurrentMap();

        // We need to split the original list of passwords we received so we can give them to a Thread
        //         using the Lists.partition() method  which returns a List of Lists
        //                                                original-list, #-of-sublists
        List<List<String>> passwordSublists = Lists.partition(passwords, passwords.size() / 4);

        /*******************************************************************************************
        // Code that was replaced for Threading
        //
        // Call the BatchPasswordHasher with the list of passwords we received
        BatchPasswordHasher batchHasher = new BatchPasswordHasher(passwords, DISCOVERED_SALT);
        batchHasher.hashPasswords();
        // Copy the results from the BatchPasswordHasher to the Map we are returning
        passwordToHashes.putAll(batchHasher.getPasswordToHashes());
        *******************************************************************************************/

        // Loop through the List of password sublist
        //      assigning each one to a BatchPasswordHasher
        //      instantiate a Thread with the BatchPasswordHasher
        //      start the Thread

        // Hold the threads so we pass this List to the method that will wait for them to complete
        List<Thread> theThreads = new ArrayList<>();

        // Objects are destroyed when they are no longer referenced
        //         and the BatchPasswordHasher() ends causing the thread to terminate
        //         the BatchPasswordHasher is destroyed when the thread terminates
        // Since we need the hashed passwords out of the BatchPasswordHasher object when it's done
        //          to copy it's result to the Map we are returning
        // We to store the BatchWordHasher before we start the thread so it will exists when Thread completes
        List<BatchPasswordHasher> theHashers = new ArrayList<>();  // Hold the BatchPasswordHasher
                                                                   //     for processing after it's thread completes

        // Loop through the List of password sublists (for-each would be OK too)
        for(int i=0; i < passwordSublists.size(); i++) {
            //  assigning each one to a BatchPasswordHasher
            BatchPasswordHasher batchHasher = new BatchPasswordHasher(passwordSublists.get(i), DISCOVERED_SALT);
            //  store the BatchPasswordHasher so we can get the hashed passwords when it's thread completes
            theHashers.add(batchHasher);

            //      instantiate a Thread with the BatchPasswordHasher
            Thread aThread = new Thread(batchHasher);
            // Add the Thread to the Thread list so we can wait for it to complete
            theThreads.add(aThread);

            //      start the Thread and continue execution - We do not wait for the thread complete
            aThread.start();  // This will invoke the run() in the object assigned to the Thread
        }
        // Now that all the threads have been started
        //     we need to wait for them all to finish before we can process their results
        waitForThreadsToComplete(theThreads);  // Give the method that will wait for threads our list threads

        // Now that all the BatchPasswordHashers have completed
        // Loop through the list of BatchPasswordHashers and copy their result to our returning result Map
        for(BatchPasswordHasher aHasher : theHashers) {
            passwordToHashes.putAll(aHasher.getPasswordToHashes()); // copy result to our returning result Map
        }

        return passwordToHashes;
    }

    /**
     * Helper method provide by original developer
     *
     * Makes the thread calling this method wait until passed in threads are done executing before proceeding.
     *
     * @param threads List of Threads to wait for completion
     * @throws InterruptedException
     */
    public static void waitForThreadsToComplete(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();  // .join() waits for the associated to complete before resuming
        }
    }

    /**
     * Writes pairs of password and its hash to a file.
     */
    static void writePasswordsAndHashes(Map<String, String> passwordToHashes) {
        File file = new File(PASSWORDS_AND_HASHES_FILE);
        try (
            BufferedWriter writer = Files.newBufferedWriter(file.toPath());
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)
        ) {
            for (Map.Entry<String, String> passwordToHash : passwordToHashes.entrySet()) {
                final String password = passwordToHash.getKey();
                final String hash = passwordToHash.getValue();

                csvPrinter.printRecord(password, hash);
            }
            System.out.println("Wrote output of batch hashing to " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
