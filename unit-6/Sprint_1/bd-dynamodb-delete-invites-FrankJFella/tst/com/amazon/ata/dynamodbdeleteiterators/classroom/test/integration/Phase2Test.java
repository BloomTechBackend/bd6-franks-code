package com.amazon.ata.dynamodbdeleteiterators.classroom.test.integration;

import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateEventActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateInviteActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.DeleteMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetInviteActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Event;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Invite;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Member;
import com.amazon.ata.dynamodbdeleteiterators.classroom.test.integration.test.helper.ActivityProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Phase2Test {
    private CreateMemberActivity createMemberActivity;
    private DeleteMemberActivity deleteMemberActivity;
    private GetInviteActivity getInviteActivity;
    private CreateInviteActivity createInviteActivity;
    private CreateEventActivity createEventActivity;

    @BeforeEach
    private void setup() {
        createMemberActivity = ActivityProvider.provideCreateMemberActivity();
        deleteMemberActivity = ActivityProvider.provideDeleteMemberActivity();
        getInviteActivity = ActivityProvider.provideGetInviteActivity();
        createInviteActivity = ActivityProvider.provideCreateInviteActivity();
        createEventActivity = ActivityProvider.provideCreateEventActivity();
    }

    @Test
    void deleteMember_withNoAcceptedInvites_deletesAllInvites() {
        // GIVEN
        Event event1 = new Event();
        event1.setName("Not attending event 1");
        event1 = createEventActivity.handleRequest(event1);

        Event event2 = new Event();
        event2.setName("Not attending event 2");
        event2 = createEventActivity.handleRequest(event2);

        Member memberToDelete = new Member();
        memberToDelete.setName("Gwen Away");
        memberToDelete = createMemberActivity.handleRequest(memberToDelete);

        Invite invite1 = new Invite();
        invite1.setEventId(event1.getId());
        invite1.setMemberId(memberToDelete.getId());
        invite1.setAttending(false);
        invite1 = createInviteActivity.handleRequest(invite1);

        Invite invite2 = new Invite();
        invite2.setEventId(event2.getId());
        invite2.setMemberId(memberToDelete.getId());
        invite2.setAttending(false);
        invite2 = createInviteActivity.handleRequest(invite2);

        // WHEN
        deleteMemberActivity.handleRequest(memberToDelete.getId());

        // THEN - both invites should have been deleted
        Invite refreshedInvite1 = getInviteActivity.handleRequest(invite1.getEventId(), memberToDelete.getId());
        Invite refreshedInvite2 = getInviteActivity.handleRequest(invite2.getEventId(), memberToDelete.getId());
        assertNull(refreshedInvite1, String.format("Expected invite %s to be deleted, but wasn't", invite1));
        assertNull(refreshedInvite2, String.format("Expected invite %s to be deleted, but wasn't", invite2));
    }

    @Test
    void deleteMember_withAnAcceptedInvite_leavesAcceptedInviteButDeletesOthers() {
        // GIVEN
        Event event1 = new Event();
        event1.setName("Accepted Event 1");
        event1 = createEventActivity.handleRequest(event1);

        Event event2 = new Event();
        event2.setName("Rejected Event 2");
        event2 = createEventActivity.handleRequest(event2);

        Member memberToDelete = new Member();
        memberToDelete.setName("Kesha Later");
        memberToDelete = createMemberActivity.handleRequest(memberToDelete);

        Invite invite1 = new Invite();
        invite1.setEventId(event1.getId());
        invite1.setMemberId(memberToDelete.getId());
        invite1.setAttending(true);
        invite1 = createInviteActivity.handleRequest(invite1);

        Invite invite2 = new Invite();
        invite2.setEventId(event2.getId());
        invite2.setMemberId(memberToDelete.getId());
        invite2.setAttending(false);
        invite2 = createInviteActivity.handleRequest(invite2);

        // WHEN
        deleteMemberActivity.handleRequest(memberToDelete.getId());

        // THEN - only one invite should have been deleted
        Invite refreshedInvite1 = getInviteActivity.handleRequest(invite1.getEventId(), memberToDelete.getId());
        Invite refreshedInvite2 = getInviteActivity.handleRequest(invite2.getEventId(), memberToDelete.getId());
        assertNotNull(refreshedInvite1, String.format("Expected invite %s NOT to be deleted, but it was", invite1));
        assertNull(refreshedInvite2, String.format("Expected invite %s to be deleted, but wasn't", invite2));
    }
}
