package com.amazon.ata.dynamodbquery.activity;

import com.amazon.ata.dynamodbquery.dao.MemberDao;
import com.amazon.ata.dynamodbquery.dao.models.Member;

import javax.inject.Inject;

/**
 * Handles a request to get a member by member ID.
 */
public class GetMemberActivity {
    private MemberDao memberDao;

    /**
     * Constructs an activity from provided member DAO.
     * @param memberDao The MemberDao
     */
    @Inject
    public GetMemberActivity(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    /**
     * Fetches and returns the member for the given member ID, if such a
     * member exists. Returns null if member not found.
     *
     * NOTE: A little deviation from usual.
     * Here we're using values directly in our arguments and return value,
     * whereas in a typical Coral service we'd have Request/Result objects
     * that would be generated from configuration via Coral. We haven't
     * created service infrastructure for this activity, so we're just
     * using the values directly.
     *
     * @param memberId The member ID to look for
     * @return The member, if found
     */
    public Member handleRequest(String memberId) {
        return memberDao.getMember(memberId);
    }
}
