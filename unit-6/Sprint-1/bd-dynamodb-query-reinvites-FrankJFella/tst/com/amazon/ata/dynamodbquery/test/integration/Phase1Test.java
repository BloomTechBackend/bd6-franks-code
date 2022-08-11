package com.amazon.ata.dynamodbquery.test.integration;

import com.amazon.ata.dynamodbquery.activity.GetEventAnnouncementsActivity;
import com.amazon.ata.dynamodbquery.dao.models.Event;
import com.amazon.ata.dynamodbquery.dao.models.EventAnnouncement;
import com.amazon.ata.dynamodbquery.test.integration.test.helper.ActivityProvider;
import com.amazon.ata.dynamodbquery.test.integration.test.helper.TestDataProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Phase1Test {
    private GetEventAnnouncementsActivity getEventAnnouncementsActivity;
    private TestDataProvider testDataProvider;

    @BeforeEach
    private void setup() {
        getEventAnnouncementsActivity = ActivityProvider.provideGetEventAnnouncementsActivity();
        testDataProvider = new TestDataProvider();
    }

    @Test
    void getEventAnnouncements_forEventWithAnnouncements_returnsAllAnnouncements() {
        // GIVEN
        Event event = testDataProvider.createEvent();
        List<EventAnnouncement> expectedAnnouncements = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            expectedAnnouncements.add(testDataProvider.createEventAnnouncement(event));
        }

        // WHEN
        List<EventAnnouncement> results = getEventAnnouncementsActivity.handleRequest(event.getId());

        assertTrue(
            results.containsAll(expectedAnnouncements),
            String.format("Expected all announcements for event (%s) to be returned but received: %s",
                expectedAnnouncements, results)
        );
    }

    @Test
    void getEventAnnouncements_forEventWithNoAnnouncements_returnsEmptyList() {
        // GIVEN
        Event event = testDataProvider.createEvent();

        // WHEN
        List<EventAnnouncement> results = getEventAnnouncementsActivity.handleRequest(event.getId());

        // THEN
        assertEquals(0, results.size(), "Expected no invites to be returned but received: " + results);
    }
}
