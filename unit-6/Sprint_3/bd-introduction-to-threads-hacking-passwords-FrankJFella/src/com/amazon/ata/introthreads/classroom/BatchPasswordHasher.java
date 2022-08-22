package com.amazon.ata.introthreads.classroom;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class to hash a batch of passwords in a separate thread.
 * To make this class able to run a Thread - implement the Runnable interface
 *                                           which requires a run() method in the class
 *
 * Immutable for concurrency
 *
 *   1. Class is final
 *   2. Instance variables are final
 *   3. No setters
 *   4. Defensive copy/return
 *   5. No member methods change the instance variables
 */
public final class BatchPasswordHasher  implements Runnable {

    private final List<String> passwords;               // Passwords to be hashed
    private final Map<String, String> passwordToHashes; // Result to be returned
                                                        // password and associated hash
    private final String salt;                          // used in hashing the password

    public BatchPasswordHasher(List<String> passwords, String salt) {
        // this.passwords = passwords;  // Replaced with defensive copy
        this.passwords = new ArrayList(passwords);
        this.salt = salt;
        passwordToHashes = new HashMap<>();
    }

    // run() method is like a main() method for the class when run on a Thread
    // when the Thread assigned to an object of this class is started
    //      the run() method in this class is invoked
    @Override
    public void run() {
         this.hashPasswords();  // run the method to hash the passwords we are passed
    }

    /**
     *  Hashes all of the passwords, and stores the hashes in the passwordToHashes Map.
     */
    public void hashPasswords() {
        try {
            for (String password : passwords) {
                final String hash = PasswordUtil.hash(password, salt);
                passwordToHashes.put(password, hash);
            }
            System.out.println(String.format("Completed hashing batch of %d passwords.", passwords.size()));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Returns a map where the key is a plain text password and the key is the hashed version of the plaintext password
     * and the class' salt value.
     *
     * @return passwordToHashes - a map of passwords to their hash value.
     */
    public Map<String, String> getPasswordToHashes() {
        return passwordToHashes;
    }

}
