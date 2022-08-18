package com.amazon.ata.inmemorycaching.classroom.dao.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

/**
 * Represents a group.
 */
@DynamoDBTable(tableName = "Caching-Groups")
public class Group {

    private String id;
    private String name;
    private GroupType type;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "type")
    public GroupType getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(GroupType type) {
        this.type = type;
    }
}
