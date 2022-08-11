package com.amazon.ata.dynamodbquery.activity;

import com.amazon.ata.dynamodbquery.dao.EventDao;
import com.amazon.ata.dynamodbquery.dao.models.Event;

import javax.inject.Inject;

/**
 * Handles requests to cancel an event.
 */
public class CancelEventActivity {
    private EventDao eventDao;

    /**
     * Constructs an Activity with the given DAO.
     * @param eventDao The EventDao to use for canceling event
     */
    @Inject
    public CancelEventActivity(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    /**
     * Cancels an event.
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param eventId The ID of the event to cancel
     * @return The updated event
     */
    public Event handleRequest(final String eventId) {
        return eventDao.cancelEvent(eventId);
    }
}
