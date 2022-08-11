package com.amazon.ata.dynamodbquery.activity;

import com.amazon.ata.dynamodbquery.dao.EventDao;
import com.amazon.ata.dynamodbquery.dao.models.Event;

import javax.inject.Inject;

/**
 * Handles requests to create a new event.
 */
public class CreateEventActivity {
    private EventDao eventDao;

    /**
     * Constructs Activity with given DAO.
     * @param eventDao The EventDao to use when creating an event
     */
    @Inject
    public CreateEventActivity(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * Creates a new event.
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param event The event to create
     * @return the created event
     */
    public Event handleRequest(final Event event) {
        return eventDao.createEvent(event);
    }
}
