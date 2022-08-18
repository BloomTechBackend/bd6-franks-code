package com.amazon.ata.inmemorycaching.classroom.integration;

import com.amazon.ata.inmemorycaching.classroom.activity.CheckUserInGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.dao.models.Group;
import com.amazon.ata.inmemorycaching.classroom.dao.models.GroupType;
import com.amazon.ata.inmemorycaching.classroom.integration.helper.ActivityProvider;
import com.amazon.ata.inmemorycaching.classroom.integration.helper.TestDataProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Phase1Test {

    private CheckUserInGroupActivity checkUserInGroupActivity;
    private TestDataProvider testDataProvider;

    @BeforeEach
    private void setup() {
        checkUserInGroupActivity = ActivityProvider.provideCheckUserInGroupActivity();
        testDataProvider = new TestDataProvider();
    }

    @Test
    public void checkUserInGroupActivity_userNotInGroup_returnsFalse() {
        // GIVEN
        Group group = testDataProvider.createGroup(testDataProvider.generateGroupName(), GroupType.DISCUSSION_GROUP);
        String userId = "userId";

        // WHEN
        boolean result = checkUserInGroupActivity.handleRequest(userId, group.getId());

        // THEN
        assertFalse(result, "Expected the check for a user not in the group to return false.");
    }

    @Test
    public void checkUserInGroupActivity_userAddedToGroup_returnsCachedFalse() {
        // GIVEN
        Group group = testDataProvider.createGroup(testDataProvider.generateGroupName(), GroupType.DISCUSSION_GROUP);
        String userId = UUID.randomUUID().toString();
        // Add to cache
        checkUserInGroupActivity.handleRequest(userId, group.getId());

        testDataProvider.addMembership(userId, group.getId());

        // WHEN
        boolean result = checkUserInGroupActivity.handleRequest(userId, group.getId());

        // THEN
        assertFalse(result, "Expected the check for a user in a group to be cached. Cache should return false" +
            "within the TTL for a user not in a group even if a call to AddUserToGroup occurs.");
    }

    @Test
    public void checkUserInGroupActivity_userInGroup_returnsTrue() {
        // GIVEN
        Group group = testDataProvider.createGroup(testDataProvider.generateGroupName(), GroupType.DISCUSSION_GROUP);
        String userId = UUID.randomUUID().toString();
        testDataProvider.addMembership(userId, group.getId());

        // WHEN
        boolean result = checkUserInGroupActivity.handleRequest(userId, group.getId());

        // THEN
        assertTrue(result, "Expected the check for a user in the group to return true.");
    }

    @Test
    public void checkUserInGroupActivity_userRemovedFromGroup_returnsCachedTrue() {
        // GIVEN
        Group group = testDataProvider.createGroup(testDataProvider.generateGroupName(), GroupType.DISCUSSION_GROUP);
        String userId = UUID.randomUUID().toString();
        testDataProvider.addMembership(userId, group.getId());

        // Add to cache
        checkUserInGroupActivity.handleRequest(userId, group.getId());

        testDataProvider.removeMembership(userId, group.getId());

        // WHEN
        boolean result = checkUserInGroupActivity.handleRequest(userId, group.getId());

        // THEN
        assertTrue(result, "Expected the check for a user in a group to be cached. Cache should return true" +
            "within the TTL for a user in a group even if a call to RemoveUserFromGroup occurs.");
    }

}
