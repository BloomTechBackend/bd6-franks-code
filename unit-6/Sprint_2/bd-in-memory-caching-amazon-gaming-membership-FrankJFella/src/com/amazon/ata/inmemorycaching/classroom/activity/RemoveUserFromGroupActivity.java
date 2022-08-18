package com.amazon.ata.inmemorycaching.classroom.activity;

import com.amazon.ata.inmemorycaching.classroom.dao.GroupMembershipDao;

import javax.inject.Inject;

/**
 * Handles requests to remove a user from a group.
 */
public class RemoveUserFromGroupActivity {

    private final GroupMembershipDao groupMembershipDao;

    /**
     * Constructs an Activity with the given DAO.
     * @param groupMembershipDao The GroupMembershipDao to use for removing the membership
     */
    @Inject
    public RemoveUserFromGroupActivity(final GroupMembershipDao groupMembershipDao) {
        this.groupMembershipDao = groupMembershipDao;
    }

    /**
     * Removes the membership for the userId in the provided group. An entry in the audit table
     * is added to track membership edits.
     * @param userId the userId to remove the membership of
     * @param groupId the group to remove the membership from
     */
    public void handleRequest(final String userId, final String groupId) {
        groupMembershipDao.removeUserFromGroup(userId, groupId);
    }
}
