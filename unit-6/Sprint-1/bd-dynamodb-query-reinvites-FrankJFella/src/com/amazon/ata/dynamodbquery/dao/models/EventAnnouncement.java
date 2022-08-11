package com.amazon.ata.dynamodbquery.dao.models;

import com.amazon.ata.dynamodbquery.converter.ZonedDateTimeConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * An announcement on a particular event.
 */
@DynamoDBTable(tableName = "DynamoDBQuery-EventAnnouncements")
public class EventAnnouncement {
    private String eventId;
    private ZonedDateTime timePublished;
    private String subject;
    private String content;

    @DynamoDBHashKey(attributeName = "eventId")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @DynamoDBRangeKey(attributeName = "timePublished")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    public ZonedDateTime getTimePublished() {
        return timePublished;
    }

    public void setTimePublished(ZonedDateTime timestamp) {
        this.timePublished = timestamp;
    }

    @DynamoDBAttribute(attributeName = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @DynamoDBAttribute(attributeName = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventAnnouncement that = (EventAnnouncement) o;
        return Objects.equals(eventId, that.eventId) &&
            Objects.equals(timePublished, that.timePublished) &&
            Objects.equals(subject, that.subject) &&
            Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, timePublished, subject, content);
    }

    @Override
    public String toString() {
        return "EventAnnouncement{" +
            "eventId='" + eventId + '\'' +
            ", timestamp=" + timePublished +
            ", subject='" + subject + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
