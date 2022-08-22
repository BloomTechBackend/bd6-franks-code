package com.amazon.ata.dynamodbdeleteiterators.classroom.dao;

import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Invite;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class InviteDaoTest {
    @InjectMocks
    private InviteDao inviteDao;

    @Mock
    private DynamoDBMapper mapper;

    @BeforeEach
    private void setup() {
        initMocks(this);
    }

    @Test
    void deleteInvite_deleteConditionsNotViolated_returnsTrue() {
        // GIVEN
        // WHEN
        boolean result = inviteDao.deleteInvite("EVENTID", "MEMBERID");

        // THEN
        assertTrue(result, "Expected deleteInvite() to return true when delete condition check not violated");
        // verify that load() is never called
        verify(mapper, never()).load(any(), any(), any());

    }

    @Test
    void deleteInvite_deleteConditionsViolated_returnsFalse() {
        // GIVEN
        doThrow(ConditionalCheckFailedException.class).when(mapper).delete(any(Invite.class), any(DynamoDBDeleteExpression.class));

        // WHEN
        boolean result = inviteDao.deleteInvite("EVENTID", "MEMBERID");

        // THEN
        assertFalse(result, "Expected deleteInvite() to return false when delete condition check violated");
        // verify that load() is never called
        verify(mapper, never()).load(any(), any(), any());
    }
}
