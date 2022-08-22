package com.amazon.ata.dynamodbquery.test.integration;

import com.amazon.ata.dynamodbquery.activity.GetInvitesForEventActivity;
import com.amazon.ata.dynamodbquery.dao.models.Event;
import com.amazon.ata.dynamodbquery.dao.models.Invite;
import com.amazon.ata.dynamodbquery.test.integration.test.helper.ActivityProvider;
import com.amazon.ata.dynamodbquery.test.integration.test.helper.TestDataProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Phase3Test {
    private GetInvitesForEventActivity getInvitesForEventActivity;
    private TestDataProvider testDataProvider;

    @BeforeEach
    private void setup() {
        getInvitesForEventActivity = ActivityProvider.provideGetInvitesForEventActivity();
        testDataProvider = new TestDataProvider();
    }

    @Test
    void getInvitesForEvent_firstPageWithLessThanTenInvites_returnsAllInvites() {
        // GIVEN
        Event event = testDataProvider.createEvent();
        List<Invite> expectedInvites = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            expectedInvites.add(testDataProvider.createAcceptedInviteForEvent(event));
        }

        // WHEN
        List<Invite> result = getInvitesForEventActivity.handleRequest(event.getId(), null);

        // THEN
        assertEquals(
            expectedInvites.size(),
            result.size(),
            "Expected to receive same number of invites for event, but received: " + result
        );
        assertTrue(
            result.containsAll(expectedInvites),
            String.format("Expected all invites for event (%s) to be returned but received: %s", expectedInvites, result)
        );
    }

    @Test
    void getInvitesForEvent_firstPageWithMoreThanTenInvites_returnsTenInvites() {
        // GIVEN
        Event event = testDataProvider.createEvent();
        List<Invite> expectedInvites = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            expectedInvites.add(testDataProvider.createRejectedInviteForEvent(event));
        }

        // WHEN
        List<Invite> result = getInvitesForEventActivity.handleRequest(event.getId(), null);

        // THEN
        assertEquals(10, result.size(), "Expected 10 invites to be returned but received: " + result);
    }

    @Test
    void getInvitesForEvent_secondPageWithMoreThanTenInvites_returnsSecondPageOfInvites() {
        // GIVEN
        Event event = testDataProvider.createEvent();
        List<Invite> expectedFirstPageOfInvites = new ArrayList<>();
        List<Invite> expectedSecondPageOfInvites = new ArrayList<>();
        String exclusiveStartMemberId = null;
        for (int i = 0; i < 10; i++) {
            Invite invite = testDataProvider.createAcceptedInviteForEvent(event);
            expectedFirstPageOfInvites.add(invite);
            if (i == 9) {
                exclusiveStartMemberId = invite.getMemberId();
            }
        }
        for (int i = 0; i < 3; i++) {
            expectedSecondPageOfInvites.add(testDataProvider.createRejectedInviteForEvent(event));
        }

        // WHEN
        List<Invite> result = getInvitesForEventActivity.handleRequest(event.getId(), exclusiveStartMemberId);

        // THEN
        assertEquals(
            expectedSecondPageOfInvites.size(),
            result.size(),
            "Received wrong number of invites, these were returned: " + result
        );
        assertTrue(
            result.containsAll(expectedSecondPageOfInvites),
            String.format(
                "Expected invites for event (%s) to be returned but received: %s",
                expectedSecondPageOfInvites,
                result)
        );
    }

    @Test
    void getInvitesForEvent_noInvites_returnsEmptyList() {
        // GIVEN
        Event event = testDataProvider.createEvent();

        // WHEN
        List<Invite> result = getInvitesForEventActivity.handleRequest(event.getId(), null);

        // THEN
        assertEquals(0, result.size(), "Expected empty list of invites to be returned but received: " + result);
    }
}
