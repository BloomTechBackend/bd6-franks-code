package com.amazon.ata.dynamodbdeleteiterators.classroom.test.integration;

import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateEventActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateInviteActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetInviteActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetInvitesForMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.CanceledInvite;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Event;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Invite;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Member;
import com.amazon.ata.dynamodbdeleteiterators.classroom.test.integration.test.helper.ActivityProvider;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Phase4Test {
    private CreateMemberActivity createMemberActivity;
    private CreateEventActivity createEventActivity;
    private CreateInviteActivity createInviteActivity;
    private GetInviteActivity getInviteActivity;
    private GetInvitesForMemberActivity getInvitesForMemberActivity;

    @BeforeEach
    private void setup() {
        createMemberActivity = ActivityProvider.provideCreateMemberActivity();
        createEventActivity = ActivityProvider.provideCreateEventActivity();
        createInviteActivity = ActivityProvider.provideCreateInviteActivity();
        getInviteActivity = ActivityProvider.provideGetInviteActivity();
        getInvitesForMemberActivity = ActivityProvider.provideGetInvitesForMemberActivity();
    }

    @Test
    void getInvitesForMemberActivity_noCanceledEvents_returnsAllInvitesAndNoInvitesCanceled() {
        // GIVEN
        Event event1 = new Event();
        event1.setName("Happening event 1");
        event1.setCanceled(false);
        event1 = createEventActivity.handleRequest(event1);

        Event event2 = new Event();
        event2.setName("Happening event 2");
        event2.setCanceled(false);
        event2 = createEventActivity.handleRequest(event2);

        Member member = new Member();
        member.setName("Gwen Twevents");
        member = createMemberActivity.handleRequest(member);

        Invite invite1 = new Invite();
        invite1.setEventId(event1.getId());
        invite1.setMemberId(member.getId());
        invite1.setAttending(true);
        invite1.setCanceled(false);
        invite1 = createInviteActivity.handleRequest(invite1);

        Invite invite2 = new Invite();
        invite2.setEventId(event2.getId());
        invite2.setMemberId(member.getId());
        invite2.setAttending(true);
        invite2.setCanceled(false);
        invite2 = createInviteActivity.handleRequest(invite2);

        List<Invite> expectedInvites = ImmutableList.of(invite1, invite2);

        // WHEN
        List<Invite> result = getInvitesForMemberActivity.handleRequest(member.getId());

        // THEN
        // We get the invites back that we expect
        assertEquals(2, result.size(), "Expected exactly two invites to be returned");
        assertTrue(
            result.containsAll(expectedInvites),
            String.format("Expected result to contain invites: %s, but contained: %s", expectedInvites, result)
        );
        for (Invite invite : result) {
            if (invite instanceof CanceledInvite) {
                fail("Expected all Invite objects, but received a CanceledInvite: " + invite);
            }
        }
        // Refresh invites and make sure they're not canceled
        Invite refreshedInvite1 = getInviteActivity.handleRequest(invite1.getEventId(), member.getId());
        assertFalse(refreshedInvite1.isCanceled(), "Expected invite not to be canceled: " + refreshedInvite1);
        Invite refreshedInvite2 = getInviteActivity.handleRequest(invite2.getEventId(), member.getId());
        assertFalse(refreshedInvite2.isCanceled(), "Expected invite not to be canceled: " + refreshedInvite2);
    }

    @Test
    void getInvitesForMemberActivity_withCanceledEvent_marksCorrespondingInviteAsCanceled() {
        // GIVEN
        Event happeningEvent = new Event();
        happeningEvent.setName("Happening event 1");
        happeningEvent.setCanceled(false);
        happeningEvent = createEventActivity.handleRequest(happeningEvent);

        Event canceledEvent = new Event();
        canceledEvent.setName("Canceled event 2");
        canceledEvent.setCanceled(true);
        canceledEvent = createEventActivity.handleRequest(canceledEvent);

        Member member = new Member();
        member.setName("Wes Uninvited");
        member = createMemberActivity.handleRequest(member);

        Invite happeningInvite = new Invite();
        happeningInvite.setEventId(happeningEvent.getId());
        happeningInvite.setMemberId(member.getId());
        happeningInvite.setAttending(true);
        happeningInvite = createInviteActivity.handleRequest(happeningInvite);

        Invite cancelingInvite = new Invite();
        cancelingInvite.setEventId(canceledEvent.getId());
        cancelingInvite.setMemberId(member.getId());
        cancelingInvite.setAttending(true);
        cancelingInvite = createInviteActivity.handleRequest(cancelingInvite);

        // WHEN
        getInvitesForMemberActivity.handleRequest(member.getId());

        // THEN
        // invite to canceled event is canceled
        Invite refreshedCancelingInvite = getInviteActivity.handleRequest(cancelingInvite.getEventId(), member.getId());
        assertTrue(
            refreshedCancelingInvite.isCanceled(),
            "Expected invite to canceled event to be marked canceled but was not: " + refreshedCancelingInvite);

        // other invite is not canceled
        Invite refreshedHappeningInvite = getInviteActivity.handleRequest(happeningInvite.getEventId(), member.getId());
        assertFalse(
            refreshedHappeningInvite.isCanceled(),
            "Expected invite not to be canceled: " + refreshedHappeningInvite);
    }

    @Test
    void getInvitesForMemberActivity_withCanceledEvent_returnsCanceledInviteInPlaceOfInvite() {
        // GIVEN
        Event event1 = new Event();
        event1.setName("Happening event 1");
        event1.setCanceled(false);
        event1 = createEventActivity.handleRequest(event1);

        Event canceledEvent = new Event();
        canceledEvent.setName("Canceling event 2");
        canceledEvent.setCanceled(true);
        canceledEvent = createEventActivity.handleRequest(canceledEvent);

        Event event3 = new Event();
        event3.setName("Happening event 3");
        event3.setCanceled(false);
        event3 = createEventActivity.handleRequest(event3);

        Member member = new Member();
        member.setName("Busy Body");
        member = createMemberActivity.handleRequest(member);

        Invite invite1 = new Invite();
        invite1.setEventId(event1.getId());
        invite1.setMemberId(member.getId());
        invite1.setAttending(true);
        invite1.setCanceled(false);
        invite1 = createInviteActivity.handleRequest(invite1);

        Invite canceledInvite = new Invite();
        canceledInvite.setEventId(canceledEvent.getId());
        canceledInvite.setMemberId(member.getId());
        canceledInvite.setAttending(true);
        canceledInvite = createInviteActivity.handleRequest(canceledInvite);

        Invite invite3 = new Invite();
        invite3.setEventId(event3.getId());
        invite3.setMemberId(member.getId());
        invite3.setAttending(true);
        invite3.setCanceled(false);
        invite3 = createInviteActivity.handleRequest(invite3);

        List<Invite> expectedHappeningInvites = ImmutableList.of(invite1, invite3);

        // WHEN
        List<Invite> result = getInvitesForMemberActivity.handleRequest(member.getId());

        // THEN
        // The invites for events still happening come back as Invites
        assertTrue(
            result.containsAll(expectedHappeningInvites),
            String.format("Expected returned invites to include invites for events still happening: %s, but was %s",
                expectedHappeningInvites,
                result)
        );
        // the canceled Invite is a CanceledInvite corresponding to the canceled invite
        canceledInvite.setCanceled(true);
        assertTrue(
            result.contains(new CanceledInvite(canceledInvite)),
            String.format("Expected returned invites to include canceled invite: %s, but was: %s",
                canceledInvite,
                result)
        );
    }
}
