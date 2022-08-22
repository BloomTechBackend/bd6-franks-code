package com.amazon.ata.dynamodbquery.activity;

import com.amazon.ata.dynamodbquery.dao.InviteDao;
import com.amazon.ata.dynamodbquery.dao.models.Invite;

import javax.inject.Inject;

/**
 * Handles requests to get an invite by event ID + member ID.
 */
public class GetInviteActivity {
    private InviteDao inviteDao;

    /**
     * Constructs a new Activity with the given DAO.
     * @param inviteDao The InviteDao to use for fetching an invite
     */
    @Inject
    public GetInviteActivity(InviteDao inviteDao) {
        this.inviteDao = inviteDao;
    }

    /**
     * Fetches an invite by event + member IDs.
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param eventId The event ID for the invite
     * @param memberId The member ID for the invite
     * @return the Invite, if found; null otherwise
     */
    public Invite handleRequest(final String eventId, final String memberId) {
        return inviteDao.getInvite(eventId, memberId);
    }
}
