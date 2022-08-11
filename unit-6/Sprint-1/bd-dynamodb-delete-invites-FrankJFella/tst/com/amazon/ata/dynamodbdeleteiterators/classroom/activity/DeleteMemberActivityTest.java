// update tests from Frank
package com.amazon.ata.dynamodbdeleteiterators.classroom.activity;

import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.InviteDao;
import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.MemberDao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DeleteMemberActivityTest {
    @InjectMocks
    private DeleteMemberActivity activity;

    @Mock
    private MemberDao memberDao;

    @Mock
    private InviteDao inviteDao;

    @BeforeEach
    private void setup() {
        initMocks(this);
    }

    @Test
    void handleRequest_attemptsToDeleteMember() {
        String memberId = "1234";
        when(inviteDao.getInvitesSentToMember(memberId)).thenReturn(Collections.emptyList());

        // WHEN
        activity.handleRequest(memberId);

        // THEN
        verify(memberDao).deletePermanently(memberId);
    }
}
