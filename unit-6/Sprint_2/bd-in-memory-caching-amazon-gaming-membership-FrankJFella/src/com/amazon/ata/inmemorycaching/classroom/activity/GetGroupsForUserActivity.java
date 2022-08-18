package com.amazon.ata.inmemorycaching.classroom.activity;

import com.amazon.ata.inmemorycaching.classroom.dao.GroupMembershipDao;
import com.amazon.ata.inmemorycaching.classroom.dao.models.GroupType;

import java.util.List;
import javax.inject.Inject;

/**
 * Handles requests to get the groups a user is a member of.
 */
public class GetGroupsForUserActivity {

    private final GroupMembershipDao groupMembershipDao;

    /**
     * Constructs an Activity with the given DAO.
     * @param groupMembershipDao The GroupMembershipDao to use for getting a users memberships
     */
    @Inject
    public GetGroupsForUserActivity(final GroupMembershipDao groupMembershipDao) {
        this.groupMembershipDao = groupMembershipDao;
    }

    /**
     * Gets a list of groupIds that a userId has a membership in. Optionally a group type can be provided.
     * If no group type is provided all groups will be returned. If it is, the groups will be filtered by
     * the provided group type.
     * @param userId - the userIf to check for group membership
     * @param groupType - an optional parameter that allows for filtering
     * @return a list of groupIds the userId is a member of, optionally filtered
     */
    public List<String> handleRequest(final String userId, final GroupType groupType) {
        if (groupType == null) {
            return groupMembershipDao.getGroupIdsForUser(userId);
        }
        return groupMembershipDao.getGroupIdsForUser(userId, groupType);
    }
}
