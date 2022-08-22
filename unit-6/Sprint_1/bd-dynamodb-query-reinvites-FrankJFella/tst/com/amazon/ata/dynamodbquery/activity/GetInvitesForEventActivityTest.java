package com.amazon.ata.dynamodbquery.activity;

import com.amazon.ata.dynamodbquery.dao.InviteDao;
import com.amazon.ata.dynamodbquery.dao.models.Invite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetInvitesForEventActivityTest {
    @InjectMocks
    private GetInvitesForEventActivity activity;

    @Mock
    private InviteDao inviteDao;

    @BeforeEach
    private void setup() {
        initMocks(this);
    }

    @Test
    void handleRequest_withNullExclusiveStartKey_callsDao() {
        // GIVEN && WHEN
        List<Invite> announcements = activity.handleRequest("EVENTID", null);

        // THEN
        verify(inviteDao).getInvitesForEvent("EVENTID", null);
    }

    @Test
    void handleRequest_withExclusiveStartKey_callsDao() {
        // GIVEN && WHEN
        List<Invite> announcements = activity.handleRequest("EVENTID", "MEMBERID");

        // THEN
        verify(inviteDao).getInvitesForEvent("EVENTID", "MEMBERID");
    }
}
