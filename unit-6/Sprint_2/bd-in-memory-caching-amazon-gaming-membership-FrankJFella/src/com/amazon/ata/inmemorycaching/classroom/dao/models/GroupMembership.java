package com.amazon.ata.inmemorycaching.classroom.dao.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

/**
 * Represents a group membership.
 */
@DynamoDBTable(tableName = "Caching-GroupMemberships")
public class GroupMembership {

    public static final String GROUP_ID_USER_ID__GSI = "groupId-userId";

    private String userId;
    private String groupId;
    private GroupType groupType;

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = GROUP_ID_USER_ID__GSI)
    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = GROUP_ID_USER_ID__GSI)
    @DynamoDBRangeKey(attributeName = "groupId")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "groupType")
    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }
}
