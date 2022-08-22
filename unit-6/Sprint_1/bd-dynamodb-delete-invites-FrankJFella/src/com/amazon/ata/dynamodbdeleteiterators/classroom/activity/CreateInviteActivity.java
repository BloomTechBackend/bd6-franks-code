package com.amazon.ata.dynamodbdeleteiterators.classroom.activity;

import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.InviteDao;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Invite;

import java.util.Objects;
import javax.inject.Inject;

/**
 * Handles requests to create an invite.
 */
public class CreateInviteActivity {
    private InviteDao inviteDao;

    /**
     * Constructs an Activity using the given DAO.
     * @param inviteDao The InviteDao to use for creating invites
     */
    @Inject
    public CreateInviteActivity(InviteDao inviteDao) {
        this.inviteDao = inviteDao;
    }

    /**
     * Creates a new invite and returns it.
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param invite The invite to create
     * @return the created invite
     */
    public Invite handleRequest(final Invite invite) {
        if (Objects.isNull(invite.getEventId())) {
            throw new IllegalArgumentException("Invite had null eventId: " + invite);
        }
        if (Objects.isNull(invite.getMemberId())) {
            throw new IllegalArgumentException("Invite had null memberId: " + invite);
        }

        return inviteDao.createInvite(invite);
    }
}
