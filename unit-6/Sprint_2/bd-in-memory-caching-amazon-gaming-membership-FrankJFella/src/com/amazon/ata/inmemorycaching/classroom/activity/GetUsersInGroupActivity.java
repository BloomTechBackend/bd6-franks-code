package com.amazon.ata.inmemorycaching.classroom.activity;

import com.amazon.ata.inmemorycaching.classroom.dao.GroupMembershipDao;

import java.util.List;
import javax.inject.Inject;

/**
 * Handles requests to get all users in a group.
 */
public class GetUsersInGroupActivity {

    private final GroupMembershipDao groupMembershipDao;

    /**
     * Constructs an Activity with the given DAO.
     * @param groupMembershipDao The GroupMembershipDao to use for getting the memberships in a group
     */
    @Inject
    public GetUsersInGroupActivity(final GroupMembershipDao groupMembershipDao) {
        this.groupMembershipDao = groupMembershipDao;
    }

    /**
     * Returns a list of userIds that have a membership in the group with id: groupId.
     * @param groupId the id of the Group to check memebership for
     * @return the userIds in the group
     */
    public List<String> handleRequest(final String groupId) {
        return groupMembershipDao.getUserIdsInGroup(groupId);
    }
}
