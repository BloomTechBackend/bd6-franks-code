package com.amazon.ata.inmemorycaching.classroom.integration.helper;

import com.amazon.ata.inmemorycaching.classroom.activity.AddUserToGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.CreateGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.activity.RemoveUserFromGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.dao.models.Group;
import com.amazon.ata.inmemorycaching.classroom.dao.models.GroupType;

import org.junit.jupiter.api.BeforeEach;

import java.util.UUID;

public class TestDataProvider {

    private CreateGroupActivity createGroupActivity = ActivityProvider.provideCreateGroupActivity();
    private AddUserToGroupActivity addUserToGroupActivity = ActivityProvider.provideAddUserToGroupActivity();
    private RemoveUserFromGroupActivity removeUserFromGroupActivity = ActivityProvider.provideRemoveUserFromGroupActivity();

    public Group createGroup(String groupName, GroupType groupType) {
        return createGroupActivity.handleRequest(groupName, groupType);
    }

    public void addMembership(String userId, String groupId) {
        addUserToGroupActivity.handleRequest(userId, groupId);
    }

    public void removeMembership(String userId, String groupId) {
        removeUserFromGroupActivity.handleRequest(userId, groupId);
    }

    public String generateGroupName() {
        return "ATA Group - " + UUID.randomUUID().toString();
    }
}
