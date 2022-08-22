package com.amazon.ata.dynamodbquery.dao;

import com.amazon.ata.dynamodbquery.dao.models.EventAnnouncement;
import com.amazon.ata.dynamodbquery.converter.ZonedDateTimeConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 * Manages access to EventAnnouncement items.
 */
public class EventAnnouncementDao {

    private DynamoDBMapper mapper;
    
    // Define a constant to represent the Date/time converter we are using
    // This is done to make it easier if we need to change the converter
    // NOT REQUIRED FOR DYNAMODB ACCESS
    private static final ZonedDateTimeConverter ZONED_DATE_TIME_CONVERTER = new ZonedDateTimeConverter();

    /**
     * Creates an EventDao with the given DDB mapper.
     * @param mapper DynamoDBMapper
     */
    @Inject
    public EventAnnouncementDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Gets all event announcements for a specific event.
     *
     * @param eventId The event to get announcements for.
     * @return the list of event announcements.
     */
    public List<EventAnnouncement> getEventAnnouncements(String eventId) {
        // TODONE: implement

        // Instantiate an object to interact with DynamoDB and set it's eventID to the one we want
        EventAnnouncement eventAnnouncement = new EventAnnouncement();
        eventAnnouncement.setEventId(eventId);

        // Define a Query Expression for the condition we want DynamoDB to apply to the data we want
        DynamoDBQueryExpression<EventAnnouncement> queryExpression =
                                                      new DynamoDBQueryExpression<EventAnnouncement>()
                                                      .withHashKeyValues(eventAnnouncement);

        // Go to DynamoDB with the Query Expression to have it return the data we want
        //                   data-type-to-retune   , conditional-expression
        return mapper.query(EventAnnouncement.class, queryExpression);
    }

    /**
     * Get all event announcements posted between the given dates for the given event.
     *
     * @param eventId The event to get announcements for.
     * @param startTime The start time to get announcements for.
     * @param endTime The end time to get announcements for.
     * @return The list of event announcements.
     */
    public List<EventAnnouncement> getEventAnnouncementsBetweenDates(String eventId, ZonedDateTime startTime,
                                                                     ZonedDateTime endTime) {
        // TODO: implement

        // Define conditions we want the data base manager to apply to our query
        // Since multiple conditions, are needed we store the condition values in a Map
        // The Map key   - an identifier for the condition
        //         value - value for condition as AttributeValue

        // identifer, condition-value
        Map<String, AttributeValue> searchValues = new HashMap<>();

        // Add the condition-values to the Map
        searchValues.put(":eventId", new AttributeValue().withS(eventId));
        searchValues.put(":startDate",
                         new AttributeValue().withS(ZONED_DATE_TIME_CONVERTER.convert(startTime)));
        searchValues.put(":endDate",
                         new AttributeValue().withS(ZONED_DATE_TIME_CONVERTER.convert(endTime)));

        // Define QueryExpression to give the data base manager containing our conditions
        DynamoDBQueryExpression<EventAnnouncement> querySearchExpression =
                              new DynamoDBQueryExpression<EventAnnouncement>()
                                      //               table-attrib  = name-of-condition
                                      //                               value-in-Map
                             .withKeyConditionExpression("eventId    = :eventId and timePublished between :startDate and :endDate")
                             .withExpressionAttributeValues(searchValues);
        return mapper.query(EventAnnouncement.class, querySearchExpression);
    }

    /**
     * Creates a new event announcement.
     *
     * @param eventAnnouncement The event announcement to create.
     * @return The newly created event announcement.
     */
    public EventAnnouncement createEventAnnouncement(EventAnnouncement eventAnnouncement) {
        mapper.save(eventAnnouncement);
        return eventAnnouncement;
    }
}
