package com.amazon.ata.dynamodbdeleteiterators.classroom.test.integration;

import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.CreateMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.DeleteMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.activity.GetMemberActivity;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Member;
import com.amazon.ata.dynamodbdeleteiterators.classroom.test.integration.test.helper.ActivityProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class Phase1Test {
    private DeleteMemberActivity deleteMemberActivity;
    private CreateMemberActivity createMemberActivity;
    private GetMemberActivity getMemberActivity;

    @BeforeEach
    private void setup() {
        deleteMemberActivity = ActivityProvider.provideDeleteMemberActivity();
        createMemberActivity = ActivityProvider.provideCreateMemberActivity();
        getMemberActivity = ActivityProvider.provideGetMemberActivity();
    }

    @Test
    void deleteMember_onExistingMember_deletesMember() {
        // GIVEN
        // Member to delete
        Member member = new Member();
        member.setName("Abby Normal");
        createMemberActivity.handleRequest(member);

        // WHEN
        deleteMemberActivity.handleRequest(member.getId());

        // THEN
        Member deletedMember = getMemberActivity.handleRequest(member.getId());
        assertNull(
            deletedMember,
            String.format("Expected no member found for ID '%s' but received %s", member.getId(), deletedMember)
        );
    }
}
