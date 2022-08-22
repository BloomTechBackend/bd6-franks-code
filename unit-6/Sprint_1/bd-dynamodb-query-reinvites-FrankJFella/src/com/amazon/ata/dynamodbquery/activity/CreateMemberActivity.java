package com.amazon.ata.dynamodbquery.activity;

import com.amazon.ata.dynamodbquery.dao.MemberDao;
import com.amazon.ata.dynamodbquery.dao.models.Member;

import javax.inject.Inject;

/**
 * Handles a request to create a new member.
 */
public class CreateMemberActivity {
    private MemberDao memberDao;

    /**
     * Constructs an activity with the provided member DAO.
     * @param memberDao The MemberDao to use
     */
    @Inject
    public CreateMemberActivity(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    /**
     * Creates a new member and returns it.
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param member The new member to create
     * @return The newly created member
     */
    public Member handleRequest(final Member member) {
        return memberDao.createMember(member);
    }
}
