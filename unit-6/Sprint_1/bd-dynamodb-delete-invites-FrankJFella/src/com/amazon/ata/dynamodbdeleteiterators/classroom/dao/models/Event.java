package com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models;

import com.amazon.ata.dynamodbdeleteiterators.classroom.converter.ZonedDateTimeConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Represents an event.
 */
@DynamoDBTable(tableName = "DynamoDBDeleteIterators-Events")
public class Event {
    private String id;
    private String organizerId;
    private ZonedDateTime time;
    private String name;
    private String description;
    private Boolean isCanceled;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "organizerId")
    public String getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    @DynamoDBAttribute(attributeName = "time")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)
    @DynamoDBAttribute(attributeName = "isCanceled")
    public Boolean isCanceled() {
        return null != isCanceled && isCanceled;
    }

    public void setCanceled(Boolean canceled) {
        isCanceled = canceled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return getId().equals(event.getId()) &&
            Objects.equals(getOrganizerId(), event.getOrganizerId()) &&
            Objects.equals(getTime(), event.getTime()) &&
            Objects.equals(getName(), event.getName()) &&
            Objects.equals(getDescription(), event.getDescription()) &&
            Objects.equals(isCanceled, event.isCanceled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrganizerId(), getTime(), getName(), getDescription(), isCanceled);
    }

    @Override
    public String toString() {
        return "Event{" +
            "id='" + id + '\'' +
            ", organizerId='" + organizerId + '\'' +
            ", time=" + time +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", isCanceled=" + isCanceled +
            '}';
    }
}
