package com.amazon.ata.dynamodbquery.dao;

import com.amazon.ata.dynamodbquery.dao.models.Invite;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class InviteDaoTest {
    private static final String TEST_EVENT_ID = "eventId";
    private static final String TEST_MEMBER_ID = "memberId";

    @InjectMocks
    private InviteDao inviteDao;

    @Mock
    private DynamoDBMapper mapper;

    @Mock
    private QueryResultPage<Invite> inviteQueryResultPage;

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
    }

    @Test
    void deleteInvite_deleteConditionsViolated_returnsFalse() {
        // GIVEN
        doThrow(ConditionalCheckFailedException.class).when(mapper).delete(any(Invite.class), any(DynamoDBDeleteExpression.class));

        // WHEN
        boolean result = inviteDao.deleteInvite("EVENTID", "MEMBERID");

        // THEN
        assertFalse(result, "Expected deleteInvite() to return false when delete condition check violated");
    }

    @Test
    public void getInvitesForEvent_nullExclusiveStartKey_queriesWithoutStartKey() {
        // GIVEN
        List<Invite> resultList = Collections.nCopies(10, new Invite());
        when(mapper.queryPage(eq(Invite.class), any(DynamoDBQueryExpression.class))).thenReturn(inviteQueryResultPage);
        when(inviteQueryResultPage.getResults()).thenReturn(ImmutableList.of()).thenReturn(resultList);

        // WHEN
        List<Invite> result = inviteDao.getInvitesForEvent(TEST_EVENT_ID, null);

        // THEN
        assertEquals(result, result, "Expected list of invites to be contained in the QueryResultPage.");

        ArgumentCaptor<DynamoDBQueryExpression<Invite>> captor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        verify(mapper).queryPage(eq(Invite.class), captor.capture());
        DynamoDBQueryExpression<Invite> capturedQueryExpression = captor.getValue();
        Invite queriedInvite = capturedQueryExpression.getHashKeyValues();
        assertEquals(TEST_EVENT_ID, queriedInvite.getEventId(), "Expected query expression to query for " +
            "partition key: " + TEST_EVENT_ID);
        assertEquals(10, capturedQueryExpression.getLimit(), "Expected a set query limit.");
        assertNull(capturedQueryExpression.getExclusiveStartKey(), "Expected the exclusive start key map to " +
            "be null when a null exclusive start key is passed in.");
    }

    @Test
    public void getInvitesForEvent_withExclusiveStartKey_queriesWithStartKey() {
        // GIVEN
        List<Invite> resultList = Collections.nCopies(10, new Invite());
        when(mapper.queryPage(eq(Invite.class), any(DynamoDBQueryExpression.class))).thenReturn(inviteQueryResultPage);
        when(inviteQueryResultPage.getResults()).thenReturn(ImmutableList.of()).thenReturn(resultList);

        // WHEN
        List<Invite> result = inviteDao.getInvitesForEvent(TEST_EVENT_ID, TEST_MEMBER_ID);

        // THEN
        assertEquals(result, result, "Expected list of invites to be contained in the QueryResultPage.");

        ArgumentCaptor<DynamoDBQueryExpression<Invite>> captor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        verify(mapper).queryPage(eq(Invite.class), captor.capture());
        DynamoDBQueryExpression<Invite> capturedQueryExpression = captor.getValue();
        Invite queriedInvite = capturedQueryExpression.getHashKeyValues();
        assertEquals(TEST_EVENT_ID, queriedInvite.getEventId(), "Expected query expression to query for " +
            "partition key: TEST_EVENT_ID");
        assertEquals(10, capturedQueryExpression.getLimit(), "Expected a set query limit.");

        assertNotNull(capturedQueryExpression.getExclusiveStartKey(), "Expected the exclusive start key map to " +
            "be populated when an exclusive start key is provided.");
        Map<String, AttributeValue> startKeyMap = capturedQueryExpression.getExclusiveStartKey();
        assertTrue(startKeyMap.containsValue(new AttributeValue().withS(TEST_EVENT_ID)),
            "Expected the exclusive start key to contain the partition key " + TEST_EVENT_ID);
        assertTrue(startKeyMap.containsValue(new AttributeValue().withS(TEST_MEMBER_ID)),
            "Expected the exclusive start key to contain the range key " + TEST_MEMBER_ID);
    }
}
