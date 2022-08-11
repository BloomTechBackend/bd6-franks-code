package com.amazon.ata.dynamodbquery.activity;

import com.amazon.ata.dynamodbquery.dao.EventAnnouncementDao;
import com.amazon.ata.dynamodbquery.dao.models.EventAnnouncement;

import javax.inject.Inject;

public class CreateEventAnnouncementActivity {
    private EventAnnouncementDao eventAnnouncementDao;

    /**
     * Constructs an Activity with the given DAO.
     * @param eventAnnouncementDao The EventDao to use for fetching event
     */
    @Inject
    public CreateEventAnnouncementActivity(EventAnnouncementDao eventAnnouncementDao) {
        this.eventAnnouncementDao = eventAnnouncementDao;
    }

    /**
     * Gets event announcements between the given startTime and endTime (inclusive).
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param eventAnnouncement The announcement to create
     * @return created announcement
     */
    public EventAnnouncement handleRequest(EventAnnouncement eventAnnouncement) {

        return eventAnnouncementDao.createEventAnnouncement(eventAnnouncement);

    }
}
