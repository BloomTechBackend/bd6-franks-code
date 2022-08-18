package com.amazon.ata.inmemorycaching.classroom.activity;

import com.amazon.ata.inmemorycaching.classroom.dao.GroupDao;
import com.amazon.ata.inmemorycaching.classroom.dao.GroupMembershipDao;
import com.amazon.ata.inmemorycaching.classroom.dao.models.Group;

import javax.inject.Inject;

/**
 * Handles requests to add a user to a group.
 */
public class AddUserToGroupActivity {

    private final GroupMembershipDao groupMembershipDao;
    private final GroupDao groupDao;

    /**
     * Constructs an Activity with the given DAOs.
     * @param groupMembershipDao The GroupMembershipDao to use for creating the membership
     * @param groupDao The GroupDao to get the Group the membership should be created for
     */
    @Inject
    public AddUserToGroupActivity(final GroupMembershipDao groupMembershipDao, final GroupDao groupDao) {
        this.groupMembershipDao = groupMembershipDao;
        this.groupDao = groupDao;
    }

    /**
     * Adds a membership for the provided userId in the provided groupId. An entry in the audit table
     * is added to track membership edits.
     * @param userId - the user to nbe added to the group
     * @param groupId - the id of the group the user should be added to
     */
    public void handleRequest(final String userId, final String groupId) {
        Group group = groupDao.getGroup(groupId);

        groupMembershipDao.addUserToGroup(userId, group);
    }
}
