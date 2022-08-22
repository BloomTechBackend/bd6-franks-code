package com.amazon.ata.dynamodbdeleteiterators.classroom.dao;

import com.amazon.ata.dynamodbdeleteiterators.classroom.dao.models.Invite;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

/**
 * Manages access to Invite items.
 */
public class InviteDao {
    private DynamoDBMapper mapper;

    /**
     * Constructs a DAO with the given mapper.
     * @param mapper The DynamoDBMapper to use
     */
    @Inject
    public InviteDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Fetches an invite by event ID and member ID.
     * @param eventId The event ID of the invite
     * @param memberId The member ID of the invite
     * @return the invite, if found; null otherwise
     */
    public Invite getInvite(String eventId, String memberId) {
        return mapper.load(Invite.class, eventId, memberId);
    }

    /**
     * Fetches all invites sent to a given member.
     * @param memberId The ID of the member to fetch invites for (sent to)
     * @return List of Invite objects sent to the given member
     */
    public List<Invite> getInvitesSentToMember(String memberId) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
            .withFilterExpression("memberId = :memberId")
            .withExpressionAttributeValues(ImmutableMap.of(":memberId", new AttributeValue(memberId)));
        return new ArrayList<>(mapper.scan(Invite.class, scanExpression));
    }

    /**
     * Creates a new invite.
     * @param invite The invite to create
     * @return The newly created invite
     */
    public Invite createInvite(Invite invite) {
        mapper.save(invite);
        return invite;
    }

    /**
     * Cancels the invite corresponding to the event + member IDs.
     * @param eventId event ID for the invite to cancel
     * @param memberId member ID for the invite to cancel
     * @return The updated Invite if found; null otherwise.
     */
    public Invite cancelInvite(String eventId, String memberId) {
        Invite invite = mapper.load(Invite.class, eventId, memberId);
        if (Objects.isNull(invite)) {
            return null;
        }

        if (!invite.isCanceled()) {
            invite.setCanceled(true);
            mapper.save(invite);
        }
        return invite;
    }

    /**
     * Deletes the invite indicated by eventId, memberId.
     * For extra safety, deletes conditional on the invite not having been
     * accepted (isAttending is false).
     * @param eventId The event the invite is for
     * @param memberId The member the invite is sent to
     * @return true if the invite was deleted; false if it was not deleted because the
     *         invite isAttending is set to true.
     */
    public boolean deleteInvite(String eventId, String memberId) {

        Invite anInvite = new Invite();  // Instantiate an object to interact with DynamoDB
        anInvite.setEventId(eventId);    // Set eventId to the eventID we want to process
        anInvite.setMemberId(memberId);  // Set memberId to the memberID we want to process

        // To have DynamoDB perform a conditional delete...
        //   1. Define a condition for the delete to be sent to DynamoDB - DynamoDBDeleteExpression
        //   2. Define the conditional expression to control the delete - ExpectedAttributeValue
        //   3. Connect the condition, the conditional expression and the table attribute(s)
        //   4. Send the combination of condition object and conditional expression to DynamoDB.
        //   5. If an exception DOES NOT occur, the delete was successful

        //   1. Define a condition for the delete to be sent to DynamoDB - DynamoDBDeleteExpression
        DynamoDBDeleteExpression aDeleteExpression = new DynamoDBDeleteExpression();

        //   2. Define the conditional expression to control the delete - ExpectedAttributeValue
        //        .withComparisionOperator() - identify the relational condition to be used
        //        .withValue() - identify the value to used in the relational condition
        //                       use AttributeValue() to specific the value desired
        ExpectedAttributeValue deleteCondition = new ExpectedAttributeValue()
                .withComparisonOperator(ComparisonOperator.EQ)    // check to see if value in the table is EQual
                .withValue(new AttributeValue().withBOOL(false)); // to false

        //   3. Connect the condition (DynamoDBDeleteExpression),
        //              the conditional expression (ExpectedAttributeValue)
        //              and the table attribute(isAttending) used in the condition
        // in Java: if(isAttending == false)
        //                                 table-attribute, ExpectedAttributeValue-object
        aDeleteExpression.withExpectedEntry("isAttending" , deleteCondition);

        // 5. If an exception DOES NOT occur, the delete was successful
        try {
            //   4. Send the combination of condition object and conditional expression to DynamoDB.
            mapper.delete(anInvite, aDeleteExpression);  // Object instantiated to interact, DynamoDBDeleteExpression
        }
        catch(ConditionalCheckFailedException anExceptionObject) {  // if the delete failed,
            return false;                                           //        return false
        }
        return true;  // If the delete did not fail, return true
    }
}
