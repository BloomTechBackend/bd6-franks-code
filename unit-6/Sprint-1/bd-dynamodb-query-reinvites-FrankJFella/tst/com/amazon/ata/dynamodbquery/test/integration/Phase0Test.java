package com.amazon.ata.dynamodbquery.test.integration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.fail;

public class Phase0Test {
    private static final String TABLE_NAME_PREFIX = "DynamoDBQuery-";
    private static final String EVENTS_TABLE_NAME = TABLE_NAME_PREFIX + "Events";
    private static final String INVITES_TABLE_NAME = TABLE_NAME_PREFIX + "Invites";
    private static final String MEMBERS_TABLE_NAME = TABLE_NAME_PREFIX + "Members";
    private static final String EVENT_ANNOUNCEMENTS_TABLE_NAME = TABLE_NAME_PREFIX + "EventAnnouncements";

    private final DynamoDB client = new DynamoDB(AmazonDynamoDBClientBuilder.standard()
        .withRegion(Regions.US_WEST_2).build());

    @ParameterizedTest
    @ValueSource(strings = {EVENTS_TABLE_NAME, INVITES_TABLE_NAME, MEMBERS_TABLE_NAME, EVENT_ANNOUNCEMENTS_TABLE_NAME})
    void expectedTable_exists(String tableName) {
        assertTableExists(tableName);
    }

    private void assertTableExists(String tableName) {
        for (Table table : client.listTables()) {
            if (table.getTableName().equals(tableName)) {
                return;
            }
        }
        fail(String.format("Did not find expected table, '%s'", tableName));
    }
}
