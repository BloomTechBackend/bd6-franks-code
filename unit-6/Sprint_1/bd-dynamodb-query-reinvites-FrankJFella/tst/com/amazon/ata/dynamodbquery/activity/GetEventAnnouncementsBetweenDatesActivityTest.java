package com.amazon.ata.dynamodbquery.activity;

import com.amazon.ata.dynamodbquery.dao.EventAnnouncementDao;
import com.amazon.ata.dynamodbquery.dao.models.EventAnnouncement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetEventAnnouncementsBetweenDatesActivityTest {
    @InjectMocks
    private GetEventAnnouncementsBetweenDatesActivity activity;

    @Mock
    private EventAnnouncementDao eventAnnouncementDao;

    @BeforeEach
    private void setup() {
        initMocks(this);
    }

    @Test
    void handleRequest_getEventAnnouncements_callsDao() {
        // GIVEN && WHEN
        ZonedDateTime start = ZonedDateTime.now();
        ZonedDateTime end = ZonedDateTime.now();

        List<EventAnnouncement> announcements = activity.handleRequest("EVENTID",
            start, end);

        // THEN
        verify(eventAnnouncementDao).getEventAnnouncementsBetweenDates("EVENTID",
                start, end);
    }
}
