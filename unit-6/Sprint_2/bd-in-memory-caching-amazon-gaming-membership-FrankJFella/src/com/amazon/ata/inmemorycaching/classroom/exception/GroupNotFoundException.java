package com.amazon.ata.inmemorycaching.classroom.exception;

/**
 * Exception used when a requested group cannot be found.
 */
public class GroupNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -273093166228140635L;

    /**
     * Constructs a GroupNotFoundException with the provided message.
     * @param message detailed message about the exception
     */
    public GroupNotFoundException(final String message) {
        super(message);
    }
}
