package com.amazon.ata.inmemorycaching.classroom.dao.models;

import com.amazon.ata.inmemorycaching.classroom.converter.ZonedDateTimeConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import java.time.ZonedDateTime;

/**
 * Represents an audit item for group membership items.
 */
@DynamoDBTable(tableName = "Caching-GroupMembershipAudits")
public class GroupMembershipAudit {

    private static final String COMPOSITE_KEY_JOINER = "_";

    private String userIdGroupIdCompositeKey;
    private ZonedDateTime editedAt;
    private GroupMembershipAuditAction auditAction;

    public GroupMembershipAudit(final String userId, final String groupId, final ZonedDateTime editedAt,
                                final GroupMembershipAuditAction auditAction) {
        this.userIdGroupIdCompositeKey = userId + COMPOSITE_KEY_JOINER + groupId;
        this.editedAt = editedAt;
        this.auditAction = auditAction;
    }

    @DynamoDBHashKey(attributeName = "userId_groupId")
    public String getId() {
        return userIdGroupIdCompositeKey;
    }

    @DynamoDBRangeKey(attributeName = "editedAt")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    public ZonedDateTime getEditedAt() {
        return editedAt;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "auditAction")
    public GroupMembershipAuditAction getAuditAction() {
        return auditAction;
    }
}
