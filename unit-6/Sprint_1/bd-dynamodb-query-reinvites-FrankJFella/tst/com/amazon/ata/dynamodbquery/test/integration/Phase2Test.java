package com.amazon.ata.dynamodbquery.test.integration;

import com.amazon.ata.dynamodbquery.activity.GetEventAnnouncementsBetweenDatesActivity;
import com.amazon.ata.dynamodbquery.dao.models.Event;
import com.amazon.ata.dynamodbquery.dao.models.EventAnnouncement;
import com.amazon.ata.dynamodbquery.test.integration.test.helper.ActivityProvider;
import com.amazon.ata.dynamodbquery.test.integration.test.helper.TestDataProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Phase2Test {
    private GetEventAnnouncementsBetweenDatesActivity getEventAnnouncementsBetweenDatesActivity;
    private TestDataProvider testDataProvider;

    @BeforeEach
    private void setup() {
        getEventAnnouncementsBetweenDatesActivity = ActivityProvider.provideGetEventAnnouncementsBetweenDatesActivity();
        testDataProvider = new TestDataProvider();
    }

    @Test
    void getEventAnnouncementsBetweenDates_forEventWithSomeAnnouncementsBetweenDates_returnsAnnouncements() {
        // GIVEN
        Event event = testDataProvider.createEvent();

        ZonedDateTime timestamp1 = ZonedDateTime.now().minusDays(7);
        EventAnnouncement announcement1 = testDataProvider.createEventAnnouncement(event, timestamp1);

        ZonedDateTime timestamp2 = ZonedDateTime.now();
        EventAnnouncement announcement2 = testDataProvider.createEventAnnouncement(event, timestamp2);

        ZonedDateTime timestamp3 = ZonedDateTime.now().plusDays(7);
        EventAnnouncement announcement3 = testDataProvider.createEventAnnouncement(event, timestamp3);

        List<EventAnnouncement> expectedAnnouncements = Arrays.asList(announcement1, announcement2);
        ZonedDateTime startDate = timestamp1.minusDays(2);
        ZonedDateTime endDate = timestamp2.plusDays(1);

        // WHEN
        List<EventAnnouncement> results = getEventAnnouncementsBetweenDatesActivity.handleRequest(event.getId(),
            startDate, endDate);

        // THEN
        assertTrue(
            results.containsAll(expectedAnnouncements),
            String.format("Expected announcements (%s) to be returned but received: %s",
                expectedAnnouncements, results)
        );
        assertFalse(results.contains(announcement3), String.format("Expected announcement %s to not be returned but was " +
            "returned in results", announcement3));
    }

    @Test
    void getEventAnnouncementsBetweenDates_forEventWithAllAnnouncementsBetweenDates_returnsAllAnnouncements() {
        // GIVEN
        Event event = testDataProvider.createEvent();

        ZonedDateTime timestamp1 = ZonedDateTime.now().minusDays(7);
        EventAnnouncement announcement1 = testDataProvider.createEventAnnouncement(event, timestamp1);

        ZonedDateTime timestamp2 = ZonedDateTime.now();
        EventAnnouncement announcement2 = testDataProvider.createEventAnnouncement(event, timestamp2);

        ZonedDateTime timestamp3 = ZonedDateTime.now().plusDays(7);
        EventAnnouncement announcement3 = testDataProvider.createEventAnnouncement(event, timestamp3);

        List<EventAnnouncement> expectedAnnouncements = Arrays.asList(announcement1, announcement2, announcement3);
        ZonedDateTime startDate = timestamp1.minusDays(2);
        ZonedDateTime endDate = timestamp3.plusDays(10);

        // WHEN
        List<EventAnnouncement> results = getEventAnnouncementsBetweenDatesActivity.handleRequest(event.getId(),
            startDate, endDate);

        // THEN
        assertTrue(
            results.containsAll(expectedAnnouncements),
            String.format("Expected all announcements (%s) to be returned but received: %s",
                expectedAnnouncements, results)
        );

    }

    @Test
    void getEventAnnouncementsBetweenDates_forEventWithNoAnnouncementsBetweenDates_returnsEmptyList() {
        // GIVEN
        Event event = testDataProvider.createEvent();

        ZonedDateTime timestamp1 = ZonedDateTime.now().minusDays(7);
        EventAnnouncement announcement1 = testDataProvider.createEventAnnouncement(event, timestamp1);

        ZonedDateTime timestamp2 = ZonedDateTime.now();
        EventAnnouncement announcement2 = testDataProvider.createEventAnnouncement(event, timestamp2);

        ZonedDateTime timestamp3 = ZonedDateTime.now().plusDays(7);
        EventAnnouncement announcement3 = testDataProvider.createEventAnnouncement(event, timestamp3);

        List<EventAnnouncement> expectedAnnouncements = Arrays.asList(announcement1, announcement2);
        ZonedDateTime startDate = timestamp1.minusDays(20);
        ZonedDateTime endDate = timestamp1.minusDays(10);

        // WHEN
        List<EventAnnouncement> results = getEventAnnouncementsBetweenDatesActivity.handleRequest(event.getId(),
            startDate, endDate);

        // THEN
        assertEquals(0, results.size(), "Expected no announcements to be returned but received: " + results);
    }

    @Test
    void getEventAnnouncementsBetweenDates_forEventWithNoAnnouncements_returnsEmptyList() {
        // GIVEN
        Event event = testDataProvider.createEvent();
        ZonedDateTime startDate = ZonedDateTime.now().minusDays(7);
        ZonedDateTime endDate = ZonedDateTime.now().plusDays(7);

        // WHEN
        List<EventAnnouncement> results = getEventAnnouncementsBetweenDatesActivity.handleRequest(event.getId(),
            startDate, endDate);

        // THEN
        assertEquals(0, results.size(), "Expected no invites to be returned but received: " + results);
    }
}
