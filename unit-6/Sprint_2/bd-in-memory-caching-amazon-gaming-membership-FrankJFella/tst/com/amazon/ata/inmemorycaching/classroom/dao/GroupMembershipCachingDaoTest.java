package com.amazon.ata.inmemorycaching.classroom.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class GroupMembershipCachingDaoTest {
    @Mock
    private GroupMembershipDao membershipDao;

    // The unit under test
    @InjectMocks
    private GroupMembershipCachingDao cachingMembershipDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void isUserInGroup_userNotInCache_delegateDaoCalled() {
        // GIVEN
        // A cache key with a known user and group
        // The delegate DAO will return true when asked if the user is in the group

        // WHEN
        // We ask the caching DAO if the user is in the group

        // THEN
        // The user was in the group
        // The delegate DAO was called
        // There were no other calls to the delegate DAO
    }

    @Test
    public void isUserInGroup_userInCache_delegateDaoNotCalled() {
        // GIVEN
        // A cache key with a known user and group
        // The delegate DAO will return true when asked if the user is in the group
        // The caching DAO has been "primed" by asking if the user is in the group

        // WHEN
        // We ask the caching DAO if the user is in the group

        // THEN
        // The user was in the group
        // The delegate DAO was called exactly once
        // There were no other calls to the delegate DAO
    }
}
