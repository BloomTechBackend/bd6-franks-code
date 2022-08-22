package com.amazon.ata.dynamodbquery.dao;

import com.amazon.ata.dynamodbquery.converter.ZonedDateTimeConverter;
import com.amazon.ata.dynamodbquery.dao.models.EventAnnouncement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EventAnnouncementDaoTest {
    private static final String TEST_EVENT_ID = "eventId";
    private static final ZonedDateTimeConverter ZONED_DATE_TIME_CONVERTER = new ZonedDateTimeConverter();

    @Mock
    DynamoDBMapper mapper;

    @Mock
    PaginatedQueryList<EventAnnouncement> queryResult;

    @InjectMocks
    EventAnnouncementDao eventAnnouncementDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getEventAnnouncements_queriesDynamoDb_returnsListFromDynamo() {
        // GIVEN
        when(mapper.query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);

        // WHEN
        List<EventAnnouncement> results = eventAnnouncementDao.getEventAnnouncements(TEST_EVENT_ID);

        // THEN
        verify(mapper).query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class));
        assertEquals(results, queryResult, "Expected getEventAnnouncements to return the query list");
    }

    // with ArgumentCaptor in verify
    @Test
    public void getEventAnnouncements_queriesDynamoDbArgCaptor_withHashKeyValues() {
        // GIVEN
        when(mapper.query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);

        // WHEN
        List<EventAnnouncement> results = eventAnnouncementDao.getEventAnnouncements(TEST_EVENT_ID);

        // THEN
        ArgumentCaptor<DynamoDBQueryExpression<EventAnnouncement>> queryExpressionArgumentCaptor =
            ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        verify(mapper).query(eq(EventAnnouncement.class), queryExpressionArgumentCaptor.capture());
        assertEquals(results, queryResult, "Expected getEventAnnouncements to return the query list");

        DynamoDBQueryExpression<EventAnnouncement> capturedQueryExpression = queryExpressionArgumentCaptor.getValue();
        EventAnnouncement hashKeyValue = capturedQueryExpression.getHashKeyValues();
        assertEquals(hashKeyValue.getEventId(), TEST_EVENT_ID, "Expected the hash key value to contain " +
            "event id: " + TEST_EVENT_ID);
    }

    // you can also use ArgumentCaptors in the when statement, essentially anywhere you would use an
    // ArgumentMatcher like eq or any
    @Test
    public void getEventAnnouncements_queriesDynamoDbArgCaptor_withHashKeyValues2() {
        // GIVEN
        ArgumentCaptor<DynamoDBQueryExpression<EventAnnouncement>> queryExpressionArgumentCaptor =
            ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        when(mapper.query(eq(EventAnnouncement.class), queryExpressionArgumentCaptor.capture())).thenReturn(queryResult);

        // WHEN
        List<EventAnnouncement> results = eventAnnouncementDao.getEventAnnouncements(TEST_EVENT_ID);

        // THEN
        verify(mapper).query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class));
        assertEquals(results, queryResult, "Expected getEventAnnouncements to return the query list");

        DynamoDBQueryExpression<EventAnnouncement> capturedQueryExpression = queryExpressionArgumentCaptor.getValue();
        EventAnnouncement hashKeyValue = capturedQueryExpression.getHashKeyValues();
        assertEquals(hashKeyValue.getEventId(), TEST_EVENT_ID, "Expected the hash key value to contain " +
            "event id: " + TEST_EVENT_ID);
    }

    // Phase 2

    @Test
    public void eventAnnouncementDao_getEventAnnouncementsBetweenDate_queriesDynamoDb() {
        // GIVEN
        ZonedDateTime startTime = ZonedDateTime.now();
        ZonedDateTime endTime = ZonedDateTime.now().plusDays(2);
        when(mapper.query(eq(EventAnnouncement.class), any(DynamoDBQueryExpression.class))).thenReturn(queryResult);

        // WHEN
        List<EventAnnouncement> results = eventAnnouncementDao.getEventAnnouncementsBetweenDates("eventId",
            startTime, endTime);

        // THEN
        ArgumentCaptor<DynamoDBQueryExpression<EventAnnouncement>> queryExpressionArgumentCaptor =
            ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        verify(mapper).query(eq(EventAnnouncement.class), queryExpressionArgumentCaptor.capture());
        assertEquals(results, queryResult, "Expected getEventAnnouncementsBetweenDates to return the query list");

        DynamoDBQueryExpression<EventAnnouncement> capturedQueryExpression = queryExpressionArgumentCaptor.getValue();
        Map<String, AttributeValue> expressionAttributeValueMap =
            capturedQueryExpression.getExpressionAttributeValues();
        assertTrue(expressionAttributeValueMap.containsValue(new AttributeValue().withS(TEST_EVENT_ID)),
            "Expected the expression attribute value map to contain the event id.");
        assertTrue(expressionAttributeValueMap.containsValue(new AttributeValue().withS(ZONED_DATE_TIME_CONVERTER.convert(startTime))),
            "Expected the expression attribute value map to contain the start time.");
        assertTrue(expressionAttributeValueMap.containsValue(new AttributeValue().withS(ZONED_DATE_TIME_CONVERTER.convert(endTime))),
            "Expected the expression attribute value map to contain the end time.");

        String keyExpression = capturedQueryExpression.getKeyConditionExpression();
        assertEquals(keyExpression, "eventId = :eventId and timePublished between :startTime and :endTime",
            "Expected the key expression to contain the event id and time published between condition");
    }
}
